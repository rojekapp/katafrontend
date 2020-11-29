package com.paimon.katahack.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paimon.katahack.R
import com.paimon.katahack.modelOngkir.ResultsItem


class OngkirAdapter(
    private val listItems: ArrayList<ResultsItem?>,
    private val listener: NoteListener
) : RecyclerView.Adapter<OngkirAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_ongkir, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = listItems[position]
        holder.tvJasa.text = item?.name
        holder.tv_harga.text = item?.costs?.get(0)!!.cost?.get(0)!!.value.toString()
        holder.itemView.setOnClickListener {
            listener.OnItemClicked(item)
        }
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvJasa = itemView.findViewById<TextView>(R.id.tv_jasa)
        var tv_harga = itemView.findViewById<TextView>(R.id.tv_ongkir)
    }

    interface NoteListener{
        fun OnItemClicked(note: ResultsItem)
    }
}
