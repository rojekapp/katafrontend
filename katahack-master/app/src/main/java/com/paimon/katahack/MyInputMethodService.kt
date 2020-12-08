package com.paimon.katahack

import android.content.ClipData
import android.content.ClipboardManager
import android.inputmethodservice.InputMethodService
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner
import com.paimon.katahack.dbLocal.TransaksiDao
import com.paimon.katahack.dbLocal.TransaksiRoomDatabase
import com.paimon.katahack.invoiceAPI.ApiClient
import com.paimon.katahack.invoiceAPI.InvoiceRequest
import com.paimon.katahack.invoiceAPI.InvoiceResponse
import com.paimon.katahack.model.Autotext
import com.paimon.katahack.model.CreateInvoice
import com.paimon.katahack.model.Invoice
import com.paimon.katahack.model.Transaction
import com.paimon.katahack.model.ongkir.Ongkir
import com.paimon.katahack.modelOngkir.OngkirRequest
import com.paimon.katahack.modelOngkir.OngkirResponse
import com.paimon.katahack.modelOngkir.ResultsItem
import com.paimon.katahack.presenter.MainPresenter
import com.paimon.katahack.presenter.MainRepository
import com.paimon.katahack.transaksiInvoice.TransaksiAdapter2
import com.paimon.katahack.transaksiInvoice.TransaksiModel
import com.paimon.katahack.ui.adapter.AutotextAdapter
import com.paimon.katahack.ui.adapter.OngkirAdapter
import com.paimon.katahack.ui.adapter.TransactionAdapter
import com.paimon.katahack.view.CreateInvoiceView
import com.paimon.katahack.view.InvoiceView
import com.paimon.katahack.view.OngkirView
import io.realm.Realm
import kotlinx.android.synthetic.main.keyboard_view.view.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MyInputMethodService : InputMethodService(), KeyboardView.OnKeyboardActionListener,
    CreateInvoiceView, InvoiceView, OngkirView {

    private lateinit var transaksi: TransaksiModel
    private lateinit var database: TransaksiRoomDatabase
    private lateinit var dao: TransaksiDao

    var radioGroup: RadioGroup? = null
    var radioButton: RadioButton? = null

    private lateinit var presenter: MainPresenter<CreateInvoiceView>
    private lateinit var invoicePresenter: MainPresenter<InvoiceView>
    private lateinit var ongkirPresenter: MainPresenter<OngkirView>
    private lateinit var keyboardView: KeyboardView
    private lateinit var keyboard: Keyboard

    private var spProgram: SmartMaterialSpinner<*>? = null
    private var programList: MutableList<String>? = null

    private lateinit var clInvoice: ConstraintLayout
    private lateinit var clOngkir: RelativeLayout
    private lateinit var clAutotext: ConstraintLayout
    private lateinit var rlSubmitInvoice: RelativeLayout
    private lateinit var clTransaction: ConstraintLayout
    private lateinit var clOngkirList: ConstraintLayout
    private lateinit var realm: Realm
    private var berat = 0
    private var destinationId = "50"
    private var originId = "20"
    private var transactionList = mutableListOf<Transaction>()
    private var autotextList = mutableListOf<Autotext>()
    private var root: View? = null
    private var caps = false

    private lateinit var apiClient: ApiClient

    override fun onCreateInputView(): View {
        root = layoutInflater.inflate(R.layout.keyboard_view, null, false)
        keyboardView = root?.keyboard_view!!
        clInvoice = root?.cl_invoice!!
        clOngkir = root?.cl_ongkir!!
        clOngkirList = root?.cl_ongkir_list!!


        rlSubmitInvoice = root?.relativeInvoice!!
        clAutotext = root?.cl_autotext!!
        clTransaction = root?.cl_transaction!!
        presenter = MainPresenter(this, MainRepository())
        invoicePresenter = MainPresenter(this, MainRepository())
        ongkirPresenter = MainPresenter(this, MainRepository())
        realm = Realm.getDefaultInstance()
        database = TransaksiRoomDatabase.getDatabase(applicationContext)
        dao = database.getNoteDao()
        apiClient = ApiClient()

        root?.btn_back?.setOnClickListener {
            keyboardView.visibility = View.VISIBLE
            clInvoice.visibility = View.GONE
            clOngkir.visibility = View.GONE
            rlSubmitInvoice.visibility = View.GONE
            clAutotext.visibility = View.GONE
            clTransaction.visibility = View.GONE
            clOngkirList.visibility = View.GONE
        }

        root?.btn_first?.setOnClickListener {
            keyboardView.visibility = View.GONE
            clInvoice.visibility = View.VISIBLE
            clOngkir.visibility = View.GONE
            rlSubmitInvoice.visibility = View.GONE
            clAutotext.visibility = View.GONE
            clTransaction.visibility = View.GONE
            clOngkirList.visibility = View.GONE


//            root?.edt_invoice?.setOnTouchListener { v, event ->
            root?.edt_invoice?.setOnFocusChangeListener { v, hasFocus ->
                Timber.e("${root?.edt_invoice?.isFocused}")
                Timber.e("${root?.edt_invoice?.text}")

                keyboardView.visibility = View.VISIBLE
            }

            root?.btn_invoice?.setOnClickListener {
                /*          val body = JsonObject()
                          body.addProperty("text", root?.edt_invoice?.text.toString())
                          presenter.setInvoice(body)*/
                val token = "c623293a-4109-4969-8dbb-c2e87dec12d"
                apiClient.getApiService().invoice(
                        "Bearer $token",
                        InvoiceRequest(root?.edt_invoice?.text.toString())
                )
                    .enqueue(object : Callback<InvoiceResponse> {
                        override fun onFailure(call: Call<InvoiceResponse>, t: Throwable) {
                            // Error logging in
                            toast("gagasa")
                        }

                        override fun onResponse(
                                call: Call<InvoiceResponse>,
                                response: Response<InvoiceResponse>
                        ) {
                            radioGroup = root?.radioGroup!!
                            val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
                            radioButton = root?.findViewById(intSelectButton)
                            var quantity = 1
                            val responseInvoice = response.body()
                            clInvoice.visibility = View.INVISIBLE
                            rlSubmitInvoice.visibility = View.VISIBLE
                            root?.btnMinus?.setOnClickListener {
                                quantity -= 1
                                root?.tv_Quantity?.text = quantity.toString()
                            }
                            root?.btn_plus?.setOnClickListener {
                                quantity += 1
                                root?.tv_Quantity?.text = quantity.toString()
                            }

                            val c: Date = Calendar.getInstance().getTime()
                            println("Current time => $c")

                            val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
                            val formattedDate: String = df.format(c)

                            root?.et_invoice_name?.setText(responseInvoice?.nama)
                            root?.et_invoice_phone?.setText(responseInvoice?.phone)
                            root?.et_harga_barang?.setText(responseInvoice?.harga.toString())
                            root?.et_lokasi?.setText(responseInvoice?.lokasi)
                            root?.btn_save_invoice?.setOnClickListener {
                                saveTransaksi(
                                        TransaksiModel(
                                                nama = root?.et_invoice_name?.text.toString(),
                                                lokasi = root?.et_lokasi?.text.toString(),
                                                phone = root?.et_invoice_phone?.text.toString(),
                                                harga = Integer.parseInt(root?.et_harga_barang?.text.toString()),
                                                info_bank = radioButton?.text.toString(),
                                                tanggal = formattedDate
                                        )
                                )

                                val clipboard = ContextCompat.getSystemService(
                                        applicationContext,
                                        ClipboardManager::class.java
                                )
                                val clip = ClipData.newPlainText("invoice", getString(R.string.copy_invoice, quantity.toString(), root?.et_invoice_name?.text.toString(), root?.et_lokasi?.text.toString(), root?.et_invoice_phone?.text.toString(), (Integer.parseInt(root?.et_harga_barang?.text.toString()) * quantity).toString(), (Integer.parseInt(root?.et_harga_barang?.text.toString()) * quantity + 10000).toString()))
                                clipboard!!.setPrimaryClip(clip)
                                toast("Invoice Copied")
                            }
                            toast("berhasil")
                        }
                    })

            }


        }

        root?.btn_second?.setOnClickListener {
            keyboardView.visibility = View.GONE
            clOngkir.visibility = View.VISIBLE
            clInvoice.visibility = View.GONE
            rlSubmitInvoice.visibility = View.GONE
            clAutotext.visibility = View.GONE
            clTransaction.visibility = View.GONE
            clOngkirList.visibility = View.GONE


    /*        val body = JsonObject()
            body.addProperty("asal", "3")
            body.addProperty("tujuan", "419")
            body.addProperty("berat", 1000)
            body.addProperty("kurir", "pos")
            root?.btn_ongkir?.setOnClickListener {
                ongkirPresenter.setOngkir(body)
            }*/

            root?.btn_ongkir?.setOnClickListener {
                apiClient.getApiService().ongkir(
                        OngkirRequest(root?.et_berat?.text.toString(), root?.et_asal_kota?.text.toString(), root?.et_tujuan_kota?.text.toString())
                )
                    .enqueue(object : Callback<OngkirResponse> {
                        override fun onFailure(call: Call<OngkirResponse>, t: Throwable) {
                            // Error logging in
                            toast("gagasa")
                        }

                        override fun onResponse(
                                call: Call<OngkirResponse>,
                                response: Response<OngkirResponse>
                        ) {
                            val responseInvoice = response.body()
                            val value = responseInvoice?.rajaongkir?.results?.get(0)!!.costs?.get(0)!!.cost?.get(0)!!.value.toString()
                            root?.tv_ongkir_total?.text = value

                            clOngkirList.visibility = View.VISIBLE
                            setupRecyclerViewOngkir(responseInvoice?.rajaongkir?.results)
                        }
                    })

            }
        }

        root?.btn_third?.setOnClickListener {
            keyboardView.visibility = View.GONE
            clAutotext.visibility = View.VISIBLE
            clOngkir.visibility = View.GONE
            rlSubmitInvoice.visibility = View.GONE
            clInvoice.visibility = View.GONE
            clTransaction.visibility = View.GONE
            clOngkirList.visibility = View.GONE
            val adapter = AutotextAdapter()
            adapter.submitList(
                    realm.where(Autotext::class.java).findAll()
            )
            root?.rv_autotext?.adapter = adapter

        }

        root?.btn_four?.setOnClickListener {
            keyboardView.visibility = View.GONE
            clTransaction.visibility = View.VISIBLE
            clOngkir.visibility = View.GONE
            clAutotext.visibility = View.GONE
            clInvoice.visibility = View.GONE
            clOngkirList.visibility = View.GONE
            getNotesData()
        }

        keyboard = Keyboard(this, R.xml.keys_layout)
        keyboardView.keyboard = keyboard
        keyboardView.setOnKeyboardActionListener(this)
        return root as View
    }

    private fun getNotesData() {
        val database = TransaksiRoomDatabase.getDatabase(applicationContext)
        val dao = database.getNoteDao()
        val listItems = arrayListOf<TransaksiModel>()
        listItems.addAll(dao.getAll())
        setupRecyclerView(listItems)
    }

    private fun setupRecyclerView(listItems: ArrayList<TransaksiModel>) {
        root?.rv_transaction?.apply {
            adapter = TransaksiAdapter2(listItems, object : TransaksiAdapter2.NoteListener {
                override fun OnItemClicked(note: TransaksiModel) {
                    Log.d("tes", "OnItemClicked: $note")
                    val clipboard = ContextCompat.getSystemService(
                            applicationContext,
                            ClipboardManager::class.java
                    )
                    val clip = ClipData.newPlainText("invoice", "Segera lakukan pembayaran sebesar RP.${note.harga} pada rekening BCA ke nomer rekening 33768271368421 atas nama Yuridenta Danu")
                    clipboard!!.setPrimaryClip(clip)
                    toast("Reminder Copied")
                }
            })
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        }
    }


    private fun setupRecyclerViewOngkir(listItems: ArrayList<ResultsItem?>) {
        root?.rv_ongkir_list?.apply {
            adapter = OngkirAdapter(listItems, object : OngkirAdapter.NoteListener {
                override fun OnItemClicked(note: ResultsItem) {

                }
            })
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onShowLoading() {
        root?.progress_keyboard?.visibility = View.VISIBLE
        clTransaction.visibility = View.GONE
        clOngkir.visibility = View.GONE
        clAutotext.visibility = View.GONE
        clInvoice.visibility = View.GONE
        rlSubmitInvoice.visibility = View.GONE
    }

    override fun onSetInvoice(data: CreateInvoice) {
        root?.progress_keyboard?.visibility = View.GONE
        clInvoice.visibility = View.VISIBLE
        Timber.e("datanya di keyboard $data")
        toast("Invoice created")
    }

    override fun onSetInvoiceError(message: String) {
        root?.progress_keyboard?.visibility = View.GONE
        clInvoice.visibility = View.VISIBLE
        toast(message)
    }

    override fun onGetInvoice(data: Invoice) {
        root?.progress_keyboard?.visibility = View.GONE
        clTransaction.visibility = View.VISIBLE
        val adapter = TransactionAdapter()
        adapter.submitList(data.data)
        root?.rv_transaction?.adapter = adapter
    }

    override fun onGetInvoiceError(message: String) {
        root?.progress_keyboard?.visibility = View.GONE
        toast(message)
    }

    override fun onSetOngkir(data: Ongkir) {
    }

    override fun onSetOngkirError(message: String) {
        root?.progress_keyboard?.visibility = View.GONE
        toast(message)
    }

    private val destinationAdapter = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            destinationId = resources.getStringArray(R.array.kota_id)[position].toString()

        }

    }

    private val originAdapter = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            originId = resources.getStringArray(R.array.kota_id)[position].toString()

        }

    }
    private val ongkirAdapterSelect = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

        override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
        ) {
            berat = resources.getStringArray(R.array.berat)[position].toInt()
        }

    }


    override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
        super.onStartInputView(info, restarting)
    }

    override fun swipeRight() {
    }

    override fun onPress(primaryCode: Int) {
    }

    override fun onRelease(primaryCode: Int) {
    }

    override fun swipeLeft() {
    }

    override fun swipeUp() {
    }

    override fun swipeDown() {
    }

    override fun onKey(primaryCode: Int, keyCodes: IntArray?) {
        val inputConnection = currentInputConnection
        if (inputConnection != null) {
            when (primaryCode) {
                Keyboard.KEYCODE_DELETE -> {
                    val selectedText = inputConnection.getSelectedText(0)

                    if (TextUtils.isEmpty(selectedText)) {
                        inputConnection.deleteSurroundingText(1, 0)
                    } else {
                        inputConnection.commitText("", 1)
                    }
                    keyboardView.invalidateAllKeys()
                }
                Keyboard.KEYCODE_SHIFT -> {
                    caps = !caps
                    keyboard.isShifted = caps
                    keyboardView.invalidateAllKeys()
                }
                Keyboard.KEYCODE_DONE -> inputConnection.sendKeyEvent(
                        KeyEvent(
                                KeyEvent.ACTION_DOWN,
                                KeyEvent.KEYCODE_ENTER
                        )
                )
                else -> {
                    var code = primaryCode.toChar()
                    if (Character.isLetter(code) && caps) {
                        code = Character.toUpperCase(code)
                    }
                    inputConnection.commitText(code.toString(), 1)
                }
            }
        }
    }

    private fun saveTransaksi(trasaksi: TransaksiModel) {

        if (dao.getById(trasaksi.id).isEmpty()) {

            dao.insert(trasaksi)
        } else {
            dao.update(trasaksi)
        }

    }

    override fun onText(text: CharSequence?) {
    }


}