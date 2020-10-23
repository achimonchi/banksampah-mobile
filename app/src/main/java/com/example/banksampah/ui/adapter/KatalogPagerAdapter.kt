package com.example.banksampah.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.banksampah.R
import com.example.banksampah.ui.fragment.tab.*

class KatalogPagerAdapter(
    fragmentManager: FragmentManager,
    context: Context
) : FragmentPagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    private var fragments = ArrayList<Fragment>()
    private val judulTab = context.resources.getStringArray(R.array.nama_tab)

    init {
        fragments = arrayListOf(
            PlastikWarnaFragment(),
            PlastikBeningFragment(),
            KertasFragment(),
            LogamFragment(),
            ElektronikFragment()
        )
    }

    override fun getCount(): Int = judulTab.size

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getPageTitle(position: Int): CharSequence? = judulTab[position]
}