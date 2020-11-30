package com.example.banksampah.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.banksampah.model.entity.SampahKategoryItem
import com.example.banksampah.utill.Resource
import com.example.banksampah.utill.Session
import com.example.banksampah.utill.Utill
import kotlinx.coroutines.launch

class RequestViewModel : BaseViewModel() {

    companion object {
        const val ACTION_REQUEST_SUCCESS = "action_request_success"
        const val ACTION_REQUEST_TIMEOUT = "action_request_timeout"
        const val ACTION_REQUEST_BTN_UPLOAD = "action_request_btn_upload"
        const val ACTION_REQUEST_URI_ISEMPTY = "action_request_uri_isempty"
    }

    val isUploaded = MutableLiveData<Boolean>()
    val jumlahEstimasi = MutableLiveData<String>()
    val note = MutableLiveData<String>()

    init {
        jumlahEstimasi.value = "0"
    }

    fun btnTambahOnClick() {
        var jumlah = jumlahEstimasi.value?.toInt() ?: 0
        jumlah++
        jumlahEstimasi.value = "$jumlah"
    }

    fun btnKurangOnClick() {
        var jumlah = jumlahEstimasi.value?.toInt() ?: 0

        if (jumlah > 0) {
            jumlah--
            jumlahEstimasi.value = "$jumlah"
        }
    }

    fun btnUploadOnClick() {
        action.value = ACTION_REQUEST_BTN_UPLOAD
    }

    fun btnTambahkanOnclick(
        item: SampahKategoryItem?,
        uri: Uri?
    ) {
        loadingEnabled.value = true
        if (uri != null) {
            viewModelScope.launch {
                when (val response =
                    repository.requestSampah(
                        Session.token ?: "",
                        item?.id ?: "",
                        jumlahEstimasi.value ?: "",
                        Utill.uriToFile(uri),
                        note.value ?: ""
                    )) {
                    is Resource.Success -> {
                        loadingEnabled.postValue(false)
                        if (response.data?.status == 200) {
                            action.postValue(ACTION_REQUEST_SUCCESS)
                        }
                    }
                    is Resource.Error -> {
                        loadingEnabled.postValue(false)
                        action.postValue(ACTION_REQUEST_TIMEOUT)
                    }
                }
            }
        } else {
            loadingEnabled.value = false
            action.value = ACTION_REQUEST_URI_ISEMPTY
        }
    }

}