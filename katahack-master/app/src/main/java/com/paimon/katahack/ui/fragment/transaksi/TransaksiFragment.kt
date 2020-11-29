package com.paimon.katahack.ui.fragment.transaksi

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
import kotlinx.android.synthetic.main.keyboard_view.view.*
import java.util.ArrayList

class TransaksiFragment : Fragment() {

    private lateinit var transaksi: TransaksiModel
    private lateinit var database: TransaksiRoomDatabase
    private lateinit var dao: TransaksiDao
    private lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_transaksi, container, false)

        database = TransaksiRoomDatabase.getDatabase(context!!)
        dao = database.getNoteDao()
        getNotesData()

        return root
    }


    private fun getNotesData() {
        val database = TransaksiRoomDatabase.getDatabase(context!!)
        val dao = database.getNoteDao()
        val listItems = arrayListOf<TransaksiModel>()
        listItems.addAll(dao.getAll())
        setupRecyclerView(listItems)
    }

    private fun setupRecyclerView(listItems: ArrayList<TransaksiModel>) {
        root.rv_transaksi?.apply {
            adapter = TransaksiAdapter(listItems, object : TransaksiAdapter.NoteListener {
                override fun OnItemClicked(note: TransaksiModel) {
                }
            })
            layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

}