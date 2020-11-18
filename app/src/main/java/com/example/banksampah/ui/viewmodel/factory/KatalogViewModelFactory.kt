package com.example.banksampah.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.banksampah.repository.MainRepository
import com.example.banksampah.ui.viewmodel.KatalogViewModel

@Suppress("UNCHECKED_CAST")
class KatalogViewModelFactory(
    private val repository: MainRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return KatalogViewModel(repository) as T
    }
}