package com.paimon.katahack.ui.fragment.transaksi

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.paimon.katahack.R
import com.paimon.katahack.dbLocal.TransaksiDao
import com.paimon.katahack.dbLocal.TransaksiRoomDatabase
import com.paimon.katahack.transaksiInvoice.TransaksiAdapter
import com.paimon.katahack.transaksiInvoice.TransaksiModel
import kotlinx.android.synthetic.main.fragment_transaksi.view.*
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SiapDikirim.newInstance] factory method to
 * create an instance of this fragment.
 */
class SiapDikirim : Fragment() {


    private lateinit var transaksi: TransaksiModel
    private lateinit var database: TransaksiRoomDatabase
    private lateinit var dao: TransaksiDao
    private lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_belum, container, false)

        database = TransaksiRoomDatabase.getDatabase(context!!)
        dao = database.getNoteDao()
        getNotesData()

        return root
    }


    private fun getNotesData() {
        val database = TransaksiRoomDatabase.getDatabase(context!!)
        val dao = database.getNoteDao()
        val listItems = arrayListOf<TransaksiModel>()
        listItems.addAll(dao.getByStatus("Siap Dikirim"))
        setupRecyclerView(listItems)
    }

    private fun setupRecyclerView(listItems: ArrayList<TransaksiModel>) {
        root.rv_transaksi?.apply {
            adapter = TransaksiAdapter(listItems, object : TransaksiAdapter.NoteListener {
                override fun OnItemClicked(note: TransaksiModel) {
                    val intent = Intent(activity, DetailTransaksi::class.java)
                    intent.putExtra("transaksi", note)
                    startActivity(intent)
                }
            })
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

}