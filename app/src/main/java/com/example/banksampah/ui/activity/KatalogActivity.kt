package com.example.banksampah.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.banksampah.R
import com.example.banksampah.databinding.ActivitySampahBinding
import com.example.banksampah.ui.adapter.KatalogPagerAdapter
import com.example.banksampah.ui.viewmodel.SampahViewModel

class KatalogActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PAGE = "extra_page"
    }

    private lateinit var sampahViewModel: SampahViewModel
    private lateinit var adapter: KatalogPagerAdapter
    private lateinit var dataBinding: ActivitySampahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sampahViewModel = ViewModelProvider(this).get(SampahViewModel::class.java)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_sampah)

        dataBinding.apply {
            lifecycleOwner = this@KatalogActivity
            viewModel = sampahViewModel
            tvSampahJudul.text = StringBuilder("Katalog Sampah")
        }

        adapter = KatalogPagerAdapter(supportFragmentManager)

        sampahViewModel.apply {
            action.observe(this@KatalogActivity, Observer { action ->
                when (action) {
                    SampahViewModel.ACTION_KATALOG_NAVIGATEUP -> onClickNavigateUp()
                    SampahViewModel.ACTION_ITEM_UPDATE -> onItemUpdate()
                }
            })
            setTitle()
        }

        dataBinding.apply {
            tablayoutKatalog.setupWithViewPager(dataBinding.viewpagerKatalog)
            viewpagerKatalog.adapter = this@KatalogActivity.adapter
            viewpagerKatalog.apply {
                postDelayed({
                    setCurrentItem(intent.getIntExtra(EXTRA_PAGE, 0), false)
                }, 100)
            }
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