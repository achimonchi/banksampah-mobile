package com.example.banksampah.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.banksampah.model.SampahResponse
import com.example.banksampah.ui.fragment.tab.TabKatalog

class KatalogPagerAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    var listTab: ArrayList<SampahResponse.Data?>? = null

    override fun getCount(): Int = listTab?.size ?: 0

    override fun getPageTitle(position: Int): CharSequence? = listTab?.get(position)?.kName

    override fun getItemPosition(`object`: Any): Int = POSITION_NONE

    override fun getItem(position: Int): Fragment {
        return when (listTab?.get(position)?.kName) {
            "Sampah Kertas" -> TabKatalog.newInstance(TabKatalog.TYPE_KERTAS)
            "Sampah Plastik" -> TabKatalog(TabKatalog.TYPE_PLASTIK)
            "Sampah Logam" -> TabKatalog(TabKatalog.TYPE_LOGAM)
            else -> TabKatalog(TabKatalog.TYPE_LAIN)
        }
    }

}