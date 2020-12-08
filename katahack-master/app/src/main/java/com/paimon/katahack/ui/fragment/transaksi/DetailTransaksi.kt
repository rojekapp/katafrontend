package com.paimon.katahack.ui.fragment.transaksi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.paimon.katahack.MainActivity
import com.paimon.katahack.R
import com.paimon.katahack.dbLocal.TransaksiDao
import com.paimon.katahack.dbLocal.TransaksiRoomDatabase
import com.paimon.katahack.transaksiInvoice.TransaksiModel
import org.jetbrains.anko.toast


class DetailTransaksi : AppCompatActivity() {

    private lateinit var tvNama: TextView
    private lateinit var tvHarga: TextView
    private lateinit var tvLokasi: TextView
    private lateinit var tvKontak: TextView
    private lateinit var btnKonfirmasi: Button



    private lateinit var database: TransaksiRoomDatabase
    private lateinit var dao: TransaksiDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.status_detail_fragment)

        val transaksiModel = intent.getParcelableExtra<TransaksiModel>("transaksi")


        database = TransaksiRoomDatabase.getDatabase(applicationContext)
        dao = database.getNoteDao()


        tvNama = findViewById(R.id.tv_nama_pembelian)
        tvHarga = findViewById(R.id.tv_harga_pembelian)
        tvLokasi = findViewById(R.id.tv_alamat_pembelian)
        tvKontak = findViewById(R.id.tv_kontak_pembelian)

        btnKonfirmasi = findViewById(R.id.btn_konfirmasi)

        tvNama.text = transaksiModel?.nama
        tvHarga.text = transaksiModel?.harga.toString()
        tvLokasi.text = transaksiModel?.lokasi
        tvKontak.text = transaksiModel?.phone


        if (transaksiModel?.status!!.equals("Belum Membayar")){
            btnKonfirmasi.setOnClickListener{
                dao.updateStatus("Sudah Membayar", transaksiModel.id)
                val intent = Intent(this, MainActivity::class.java)
                toast("Status sudah berganti menjadi sudah dibayar")
                startActivity(intent)
            }
        }
        else if(transaksiModel.status.equals("Sudah Membayar")){
            btnKonfirmasi.setOnClickListener{
                dao.updateStatus("Siap Dikirim", transaksiModel.id)
                val intent = Intent(this, MainActivity::class.java)
                toast("Status sudah berganti menjadi siap dikirim")
                startActivity(intent)
            }
        }

        if (transaksiModel.status.equals("Siap Dikirim")){
                btnKonfirmasi.isVisible = false
            }

    }
}