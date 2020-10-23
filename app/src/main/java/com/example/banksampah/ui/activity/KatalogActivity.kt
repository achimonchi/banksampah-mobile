package com.example.banksampah.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.banksampah.R
import com.example.banksampah.ui.adapter.KatalogPagerAdapter
import kotlinx.android.synthetic.main.activity_katalog.*

class KatalogActivity : AppCompatActivity(R.layout.activity_katalog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewpager_katalog.adapter = KatalogPagerAdapter(supportFragmentManager, this)
        tablayout_katalog.setupWithViewPager(viewpager_katalog)
    }

}