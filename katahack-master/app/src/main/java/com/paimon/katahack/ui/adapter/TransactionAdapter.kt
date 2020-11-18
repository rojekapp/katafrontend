package com.paimon.katahack.ui.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.paimon.katahack.R
import com.paimon.katahack.databinding.ListTransactionBinding
import com.paimon.katahack.model.InvoiceData
import timber.log.Timber
import java.text.SimpleDateFormat


class TransactionAdapter :
    ListAdapter<InvoiceData, TransactionAdapter.ViewHolder>(
        TransactionDiffCallback()
    ) {

    private lateinit var viewHolder: ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        viewHolder = ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_transaction, parent, false
            ), parent.context
        )
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ListTransactionBinding, private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(invoiceData: InvoiceData) {

            binding.setClickListener { view ->
                Timber.e("easdfasdf")
                val intent = Intent()
                intent.flags = FLAG_ACTIVITY_NEW_TASK
                intent.setClassName(context, "com.paimon.katahack.MainActivity")
                context.startActivity(intent)
                val clipboard =
                    ContextCompat.getSystemService(context, ClipboardManager::class.java)
                val text= "Silakan menyelesaikan pembayaran sebesar ${invoiceData.harga}"
                val clip = ClipData.newPlainText("label", text)
                clipboard!!.setPrimaryClip(clip)
                Toast.makeText(context, "copied", Toast.LENGTH_SHORT).show()
            }
            //                navigateToBiddingDetail(offer, view)
            with(binding) {
                invoice = invoiceData
                Timber.e("${invoiceData.createdAt.substring(0, 10)}")
                val formatter = SimpleDateFormat("yyyy-MM-dd")
                val formatter2 = SimpleDateFormat("dd MMM yyyy")
                val date = formatter.parse(invoiceData.createdAt.substring(0, 10))
                val newDate = formatter2.format(date)
                tvDateTransaction.text = newDate
                executePendingBindings()
            }
        }
    }

//        private fun navigateToBiddingDetail(offer: Offer, view: View) {
//            val direction =
//                BiddingFragmentDirections.actionBiddingToBiddingDetail(offer)
//            view.findNavController().navigate(direction)
//        }
}


private class TransactionDiffCallback : DiffUtil.ItemCallback<InvoiceData>() {
    override fun areItemsTheSame(oldItem: InvoiceData, newItem: InvoiceData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: InvoiceData, newItem: InvoiceData): Boolean {
        return oldItem == newItem
    }
}