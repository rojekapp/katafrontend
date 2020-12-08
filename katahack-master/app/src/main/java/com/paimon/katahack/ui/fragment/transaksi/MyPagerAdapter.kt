package com.paimon.katahack.ui.fragment.transaksi

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

const val BELUM_INDEX = 0
const val SUDAH_INDEX = 1
const val SIAP_INDEX = 2
const val TELAH_INDEX = 3

class MyPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        BELUM_INDEX to { BelumFragment() },
        SUDAH_INDEX to { TransaksiFragment() },
        SIAP_INDEX to { SiapDikirim() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}