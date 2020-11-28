package com.example.banksampah.ui.activity

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.banksampah.R
import com.example.banksampah.databinding.ActivityKatalogBinding
import com.example.banksampah.model.SampahResponse
import com.example.banksampah.ui.adapter.KatalogPagerAdapter
import com.example.banksampah.ui.viewmodel.KatalogViewModel
import com.example.banksampah.utill.Resource
import com.example.banksampah.utill.Session
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class KatalogActivity : AppCompatActivity() {

    companion object {
        const val ARG_PAGE = "arg_page"
    }

    private lateinit var katalogViewModel: KatalogViewModel
    private lateinit var adapter: KatalogPagerAdapter
    lateinit var dataBinding: ActivityKatalogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        katalogViewModel = ViewModelProvider(this).get(KatalogViewModel::class.java)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_katalog)

        dataBinding.apply {
            lifecycleOwner = this@KatalogActivity
            viewModel = katalogViewModel
        }

        adapter = KatalogPagerAdapter(supportFragmentManager)

        katalogViewModel.apply {
            action.observe(this@KatalogActivity, Observer { action ->
                when (action) {
                    KatalogViewModel.ACTION_KATALOG_NAVIGATEUP -> onClickNavigateUp()
                }
            })
            setTitle(adapter)
        }

        dataBinding.apply {
            tablayoutKatalog.setupWithViewPager(dataBinding.viewpagerKatalog)
            viewpagerKatalog.adapter = this@KatalogActivity.adapter
        }
    }

    override fun onResume() {
        super.onResume()
        dataBinding.viewpagerKatalog.apply {
            postDelayed({
                this.currentItem = intent.getIntExtra(ARG_PAGE, 0)
            }, 100)
        }
    }

    private fun onClickNavigateUp() {
        finish()
    }

}