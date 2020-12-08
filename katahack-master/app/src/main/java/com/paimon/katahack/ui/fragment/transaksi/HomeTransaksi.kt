package com.paimon.katahack.ui.fragment.transaksi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayoutMediator
import com.paimon.katahack.R
import com.paimon.katahack.databinding.FragmentHomeTransaksiBinding
import kotlinx.android.synthetic.main.fragment_home_transaksi.view.*


class HomeTransaksi : Fragment() {

    companion object {
        fun newInstance() = HomeTransaksi()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeTransaksiBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabStatus
        val viewPager = binding.vpStatus

        viewPager.adapter = MyPagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

//        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            BELUM_INDEX -> getString(R.string.belum_bayar)
            SUDAH_INDEX -> getString(R.string.sudah_bayar)
            SIAP_INDEX -> "Siap Dikirim"
            else -> null
        }
    }

}


