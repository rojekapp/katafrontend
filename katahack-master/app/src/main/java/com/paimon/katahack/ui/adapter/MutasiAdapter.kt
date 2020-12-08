package com.paimon.katahack.ui.adapter

import com.paimon.katahack.model.MutasiModel
import com.paimon.katahack.transaksiInvoice.TransaksiModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.paimon.katahack.R

class MutasiAdapter(
    private val listItems: ArrayList<MutasiModel>,
    private val listener: NoteListener
) : RecyclerView.Adapter<MutasiAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_mutasi_2, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = listItems[position]

        holder.textViewTitle.text = item.tanggalTransaksi
        holder.textViewBody.text = item.JumlahTransaksi

    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle = itemView.findViewById<TextView>(R.id.tv_date_mutasi)
        var textViewBody = itemView.findViewById<TextView>(R.id.tv_amount_mutasi)
    }

    interface NoteListener{
        fun OnItemClicked(note: MutasiModel)
    }
}

