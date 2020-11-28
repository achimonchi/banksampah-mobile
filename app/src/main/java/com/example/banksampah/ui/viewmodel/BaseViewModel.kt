package com.example.banksampah.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.banksampah.repository.MainRepository

open class BaseViewModel : ViewModel() {

    var repository: MainRepository = MainRepository()

    val action = MutableLiveData<String>()
    val loadingEnabled = MutableLiveData<Boolean>()

}