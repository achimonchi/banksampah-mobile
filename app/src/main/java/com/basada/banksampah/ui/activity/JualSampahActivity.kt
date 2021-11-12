package com.basada.banksampah.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.basada.banksampah.R
import com.basada.banksampah.databinding.ActivitySampahBinding
import com.basada.banksampah.ui.adapter.JualSampahPagerAdapter
import com.basada.banksampah.ui.viewmodel.SampahViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

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

        }
        sampahViewModel.setTitle()

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