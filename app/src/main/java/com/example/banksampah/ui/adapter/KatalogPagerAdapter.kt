package com.example.banksampah.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.banksampah.model.SampahResponse
import com.example.banksampah.ui.fragment.tab.KertasFragment
import com.example.banksampah.ui.fragment.tab.LainnyaFragment
import com.example.banksampah.ui.fragment.tab.LogamFragment
import com.example.banksampah.ui.fragment.tab.PlastikFragment

class KatalogPagerAdapter(
    fragmentManager: FragmentManager,
    private val listTab: ArrayList<SampahResponse.Data?>?
) : FragmentPagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    override fun getCount(): Int = listTab?.size ?: 0

    override fun getPageTitle(position: Int): CharSequence? = listTab?.get(position)?.kName

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun getItem(position: Int): Fragment {
        return when (listTab?.get(position)?.kName) {
            "Sampah Kertas" -> KertasFragment()
            "Sampah Plastik" -> PlastikFragment()
            "Sampah Logam" -> LogamFragment()
            else -> LainnyaFragment()
        }
    }

}