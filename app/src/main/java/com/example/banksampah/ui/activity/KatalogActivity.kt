package com.example.banksampah.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.banksampah.R
import com.example.banksampah.model.SampahResponse
import com.example.banksampah.ui.adapter.KatalogPagerAdapter
import com.example.banksampah.ui.viewmodel.KatalogViewModel
import com.example.banksampah.utill.Resource
import com.example.banksampah.utill.Session
import kotlinx.android.synthetic.main.activity_katalog.*

class KatalogActivity : AppCompatActivity(R.layout.activity_katalog) {

    private lateinit var katalogViewModel: KatalogViewModel
    private lateinit var adapter: KatalogPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Session.init(this)

        katalogViewModel = ViewModelProvider(this).get(KatalogViewModel::class.java)

        val listTab: ArrayList<SampahResponse.Data?>? = ArrayList()

        adapter = KatalogPagerAdapter(supportFragmentManager, listTab)

        katalogViewModel.getSampahCategory(Session.token!!)
        katalogViewModel.sampahCategory.observe(this, Observer {
            when (it) {
                is Resource.Success -> {
                    it.data?.data?.forEach { item ->
                        item.let {
                            listTab?.add(item)
                        }
                    }
                    adapter.notifyDataSetChanged()
                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {

                }
            }
        })

        viewpager_katalog.adapter = adapter
        tablayout_katalog.setupWithViewPager(viewpager_katalog)
    }

}