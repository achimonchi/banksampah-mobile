package com.example.banksampah.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.banksampah.repository.MainRepository

open class BaseViewModel : ViewModel() {

    val action = MutableLiveData<String>()
    val loadingEnabled = MutableLiveData<Boolean>()
    protected val repository = MainRepository()

}