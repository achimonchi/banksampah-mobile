package com.basada.banksampah.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.basada.banksampah.R
import com.basada.banksampah.databinding.ActivitySampahBinding
import com.basada.banksampah.ui.adapter.KatalogPagerAdapter
import com.basada.banksampah.ui.viewmodel.SampahViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KatalogActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PAGE = "extra_page"
    }

    private val sampahViewModel: SampahViewModel by viewModels()
    private lateinit var adapter: KatalogPagerAdapter
    private lateinit var dataBinding: ActivitySampahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                    setCurrentItem(intent.getIntExtra(EXTRA_PAGE, 0), true)
                }, 300)
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