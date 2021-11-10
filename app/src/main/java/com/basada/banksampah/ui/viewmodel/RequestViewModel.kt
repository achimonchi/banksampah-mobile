package com.basada.banksampah.ui.viewmodel

import android.net.Uri
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.basada.banksampah.model.entity.JadwalResponseItem
import com.basada.banksampah.model.entity.RequestAdminSampahItem
import com.basada.banksampah.model.entity.SampahKategoryItem
import com.basada.banksampah.repository.MainRepository
import com.basada.banksampah.utill.Resource
import com.basada.banksampah.utill.Session
import com.basada.banksampah.utill.Utill
import kotlinx.coroutines.launch
import java.lang.Exception
import java.net.SocketTimeoutException
import java.text.DecimalFormat

class RequestViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : BaseViewModel() {

    companion object {
        const val ACTION_REQUEST_SUCCESS = "action_request_success"
        const val ACTION_REQUEST_TIMEOUT = "action_request_timeout"
        const val ACTION_REQUEST_BTN_UPLOAD = "action_request_btn_upload"
        const val ACTION_REQUEST_URI_ISEMPTY = "action_request_uri_isempty"
        const val ACTION_REQUEST_TOO_MIN = "action_request_too_min"
        const val ACTION_REQUEST_WEIGHT_NULL = "action_request_weight_null"
        const val ACTION_REQUEST_MIN_FETCHED = "action_request_min_fetched"
    }

    val isUploaded = MutableLiveData<Boolean>()
    val jumlahEstimasi = MutableLiveData<String>()
    val note = MutableLiveData<String>()
    val listItem = MutableLiveData<List<Any>>()
    val selectedItem = MutableLiveData<Int>()
    val minSampah = MutableLiveData<String>()

    val loadingMinMax = MutableLiveData<Boolean>()

    val listItemAdmin = MutableLiveData<List<Any>>()
    val selectedItemAdmin = MutableLiveData<Int>()

    init {
        minSampah.value = "0"
        loadingMinMax.value = true
        fetchItem()
        fetchMin()
        fetchItemItemAdmin()
    }

    private fun fetchMin() {
        viewModelScope.launch {
            try {
                when (val response = repository.getMinSampah(Session.token ?: "")) {
                    is Resource.Success -> {
                        minSampah.postValue(response.data)
                        loadingMinMax.postValue(false)
                    }
                    is Resource.Error -> {
                        action.postValue(ACTION_REQUEST_TIMEOUT)
                        loadingMinMax.postValue(false)
                    }
                }
            } catch (e: SocketTimeoutException) {
                action.postValue(ACTION_REQUEST_TIMEOUT)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun fetchItemItemAdmin() {
        viewModelScope.launch {
            try {
                when (val response = repository.getAdminInduk(Session.token ?: "")) {
                    is Resource.Success -> {
                        val listItemAdminTemp = response.data

                        listItemAdmin.postValue(listItemAdminTemp)
                    }
                    is Resource.Error -> {
                        action.postValue(ACTION_REQUEST_TIMEOUT)
                    }
                }
            } catch (e: SocketTimeoutException) {
                action.postValue(ACTION_REQUEST_TIMEOUT)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun fetchItem() {
        viewModelScope.launch {
            try {
                when (val response = repository.getJadwalSampah(Session.token ?: "")) {
                    is Resource.Success -> {
                        val listItemJadwal = response.data

                        listItem.postValue(listItemJadwal)
                    }
                    is Resource.Error -> {
                        action.postValue(ACTION_REQUEST_TIMEOUT)
                    }
                }
            } catch (e: SocketTimeoutException) {
                action.postValue(ACTION_REQUEST_TIMEOUT)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun btnTambahOnClick() {
        jumlahEstimasi.value?.let {
            var jumlah: Double = it.trim().toDouble()
            jumlah += 0.01

            val decimalFormat = DecimalFormat("#.##")

            jumlahEstimasi.value = decimalFormat.format(jumlah)
        }
    }

    fun btnKurangOnClick() {
        var jumlah: Double = jumlahEstimasi.value?.toDouble() ?: 0.0

        if (jumlah > minSampah.value?.toDouble() ?: 0.0) {
            jumlah -= 0.01

            val decimalFormat = DecimalFormat("#.##")

            jumlahEstimasi.value = decimalFormat.format(jumlah)
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
            if (jumlahEstimasi.value != null || jumlahEstimasi.value != "") {
                if (jumlahEstimasi.value.toString().toFloat() >=
                    minSampah.value.toString().toFloat()
                ) {
                    val itemSelected =
                        listItem.value?.get(selectedItem.value ?: 0) as JadwalResponseItem
                    val itemSelectedAdmin =
                        listItemAdmin.value?.get(
                            selectedItemAdmin.value ?: 0
                        ) as RequestAdminSampahItem

                    viewModelScope.launch {
                        try {
                            when (val response =
                                repository.requestSampah(
                                    Session.token ?: "",
                                    item?.id ?: "",
                                    jumlahEstimasi.value ?: "",
                                    Utill.uriToFile(uri),
                                    itemSelected.id ?: "",
                                    itemSelectedAdmin.id ?: "",
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
                        } catch (e: SocketTimeoutException) {
                            action.postValue(ACTION_REQUEST_TIMEOUT)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                } else {
                    loadingEnabled.value = false
                    action.value = ACTION_REQUEST_TOO_MIN
                }
            } else {
                loadingEnabled.value = false
                action.value = ACTION_REQUEST_WEIGHT_NULL
            }
        } else {
            loadingEnabled.value = false
            action.value = ACTION_REQUEST_URI_ISEMPTY
        }
    }

}
