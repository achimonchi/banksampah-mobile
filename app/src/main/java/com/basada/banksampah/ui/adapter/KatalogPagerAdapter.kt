package com.basada.banksampah.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.basada.banksampah.model.entity.SampahItem
import com.basada.banksampah.ui.fragment.tab.TabKatalogFragment

class KatalogPagerAdapter(
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
            "Sampah Kertas" -> TabKatalogFragment(TabKatalogFragment.TYPE_KERTAS)
            "Sampah Plastik" -> TabKatalogFragment(TabKatalogFragment.TYPE_PLASTIK)
            "Sampah Logam" -> TabKatalogFragment(TabKatalogFragment.TYPE_LOGAM)
            else -> TabKatalogFragment(TabKatalogFragment.TYPE_LAIN)
        }
    }

}