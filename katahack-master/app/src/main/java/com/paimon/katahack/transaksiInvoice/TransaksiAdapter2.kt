package com.paimon.katahack.transaksiInvoice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.paimon.katahack.R

class TransaksiAdapter2(
        private val listItems: ArrayList<TransaksiModel>,
        private val listener: NoteListener
) : RecyclerView.Adapter<TransaksiAdapter2.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_transaction, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = listItems[position]
        holder.textViewTitle.text = item.nama
        holder.textViewBody.text = item.phone
        holder.textViewStatus.text = item.status
        holder.textViewlokasi.text = item.lokasi
        holder.textViewTanggal.text = item.tanggal
        holder.textViewHarga.text = item.harga.toString()
        holder.itemView.setOnClickListener {
            listener.OnItemClicked(item)
        }
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle = itemView.findViewById<TextView>(R.id.tv_name_transaction)
        var textViewBody = itemView.findViewById<TextView>(R.id.tv_number_transaction)
        var textViewlokasi = itemView.findViewById<TextView>(R.id.tv_date_transaction)
        var textViewStatus = itemView.findViewById<TextView>(R.id.tv_status_transaction)
        var textViewHarga = itemView.findViewById<TextView>(R.id.tv_amount_transaction)
        var textViewTanggal = itemView.findViewById<TextView>(R.id.tv_tanggal_transaksi)
    }

    interface NoteListener{
        fun OnItemClicked(note: TransaksiModel)
    }
}

