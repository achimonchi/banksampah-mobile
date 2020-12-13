package com.example.banksampah.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.banksampah.R
import com.example.banksampah.databinding.ActivitySampahBinding
import com.example.banksampah.ui.adapter.JualSampahPagerAdapter
import com.example.banksampah.ui.viewmodel.SampahViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JualSampahActivity : AppCompatActivity() {

    private val sampahViewModel: SampahViewModel by viewModels()
    private lateinit var adapter: JualSampahPagerAdapter
    private lateinit var dataBinding: ActivitySampahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_sampah)

        dataBinding.apply {
            lifecycleOwner = this@JualSampahActivity
            viewModel = sampahViewModel
            tvSampahJudul.text = StringBuilder("Jual Sampah")
        }

        adapter = JualSampahPagerAdapter(supportFragmentManager)

        sampahViewModel.apply {
            action.observe(this@JualSampahActivity, Observer { action ->
                when (action) {
                    SampahViewModel.ACTION_KATALOG_NAVIGATEUP -> onClickNavigateUp()
                    SampahViewModel.ACTION_ITEM_UPDATE -> onItemUpdate()
                }
            })
            setTitle()
        }

        dataBinding.apply {
            tablayoutKatalog.setupWithViewPager(dataBinding.viewpagerKatalog)
            viewpagerKatalog.adapter = this@JualSampahActivity.adapter
        }
    }

    private fun onItemUpdate() {
        adapter.listTab = sampahViewModel.listTitle
        adapter.notifyDataSetChanged()
    }

    private fun onClickNavigateUp() {
        finish()
    }

}