package com.basada.banksampah.ui.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.basada.banksampah.model.entity.SampahItem
import com.basada.banksampah.ui.fragment.tab.TabJualSampahFragment

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
        val id = listTab?.get(position)?.id.toString();
        return TabJualSampahFragment(TabJualSampahFragment.TYPE_KERTAS, id)
//        return TabJualSampahFragment(listTab?.get(position)?.kName.toString(), id)
//        return when (listTab?.get(position)?.kName) {
//            "Sampah Kertas" -> TabJualSampahFragment(TabJualSampahFragment.TYPE_KERTAS)
//            "Sampah Plastik" -> TabJualSampahFragment(TabJualSampahFragment.TYPE_PLASTIK)
//            "Sampah Logam" -> TabJualSampahFragment(TabJualSampahFragment.TYPE_LOGAM)
//            else -> TabJualSampahFragment(TabJualSampahFragment.TYPE_LAIN)
//        }
    }

}