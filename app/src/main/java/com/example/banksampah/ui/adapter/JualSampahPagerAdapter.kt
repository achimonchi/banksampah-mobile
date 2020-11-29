package com.example.banksampah.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.banksampah.model.SampahItem
import com.example.banksampah.ui.fragment.tab.TabJualSampahFragment

class JualSampahPagerAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    var listTab: ArrayList<SampahItem>? = null

    override fun getCount(): Int = listTab?.size ?: 0

    override fun getPageTitle(position: Int): CharSequence? = listTab?.get(position)?.kName

    override fun getItemPosition(`object`: Any): Int = POSITION_NONE

    override fun getItem(position: Int): Fragment {
        return when (listTab?.get(position)?.kName) {
            "Sampah Kertas" -> TabJualSampahFragment(TabJualSampahFragment.TYPE_KERTAS)
            "Sampah Plastik" -> TabJualSampahFragment(TabJualSampahFragment.TYPE_PLASTIK)
            "Sampah Logam" -> TabJualSampahFragment(TabJualSampahFragment.TYPE_LOGAM)
            else -> TabJualSampahFragment(TabJualSampahFragment.TYPE_LAIN)
        }
    }

}