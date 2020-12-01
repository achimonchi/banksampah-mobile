package com.example.banksampah.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    val action = MutableLiveData<String>()
    val loadingEnabled = MutableLiveData<Boolean>()

}