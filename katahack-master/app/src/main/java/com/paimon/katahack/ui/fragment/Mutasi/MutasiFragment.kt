package com.paimon.katahack.ui.fragment.Mutasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.paimon.katahack.R
import com.paimon.katahack.dbLocal.TransaksiDao
import com.paimon.katahack.dbLocal.TransaksiRoomDatabase
import com.paimon.katahack.model.MutasiModel
import com.paimon.katahack.transaksiInvoice.TransaksiAdapter
import com.paimon.katahack.transaksiInvoice.TransaksiModel
import com.paimon.katahack.ui.adapter.MutasiAdapter
import kotlinx.android.synthetic.main.fragment_mutasi.view.*
import kotlinx.android.synthetic.main.fragment_transaksi.view.*
import kotlinx.android.synthetic.main.fragment_transaksi.view.rv_transaksi
import java.util.ArrayList

class MutasiFragment : Fragment() {


    private lateinit var transaksi: TransaksiModel
    private lateinit var database: TransaksiRoomDatabase
    private lateinit var dao: TransaksiDao
    private lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_mutasi, container, false)

        database = TransaksiRoomDatabase.getDatabase(context!!)
        dao = database.getNoteDao()
        getNotesData()

        return root
    }


    private fun getNotesData() {
        val database = TransaksiRoomDatabase.getDatabase(context!!)
        val dao = database.getNoteDao()
        val listItems = arrayListOf<MutasiModel>()
        listItems.addAll(arrayOf(MutasiModel(1,"20 Februari 2020", "Rp 100.000"), MutasiModel(2,"21 Februari 2020", "Rp 124.000")))
        setupRecyclerView(listItems)
    }

    private fun setupRecyclerView(listItems: ArrayList<MutasiModel>) {
        root.rv_mutasi?.apply {
            adapter = MutasiAdapter(listItems, object : MutasiAdapter.NoteListener {
                override fun OnItemClicked(note: MutasiModel) {
                }
            })
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }
}