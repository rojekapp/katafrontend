package com.paimon.katahack.ui.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.paimon.katahack.R
import com.paimon.katahack.databinding.ListAutotextBinding
import com.paimon.katahack.model.Autotext


class AutotextAdapter :
    ListAdapter<Autotext, AutotextAdapter.ViewHolder>(
        AutotextDiffCallback()
    ) {

    private lateinit var viewHolder: ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        viewHolder = ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_autotext, parent, false
            ), parent.context
        )
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ListAutotextBinding, private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(autotext: Autotext) {

            binding.setClickListener { view ->
                val clipboard = getSystemService(context, ClipboardManager::class.java)
                val clip = ClipData.newPlainText("label", autotext.content)
                clipboard!!.setPrimaryClip(clip)
                Toast.makeText(context, "copied", Toast.LENGTH_SHORT).show()
            }

            with(binding) {
                setAutotext(autotext)
                executePendingBindings()
            }
        }

//        private fun navigateToBiddingDetail(offer: Offer, view: View) {
//            val direction =
//                BiddingFragmentDirections.actionBiddingToBiddingDetail(offer)
//            view.findNavController().navigate(direction)
//        }
    }
}

private class AutotextDiffCallback : DiffUtil.ItemCallback<Autotext>() {
    override fun areItemsTheSame(oldItem: Autotext, newItem: Autotext): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Autotext, newItem: Autotext): Boolean {
        return oldItem == newItem
    }
}