package com.example.banksampah.ui.viewmodel

class HomeViewModel : BaseViewModel() {

    companion object {
        const val ACTION_HOME_KTLG_KERTAS = "action_home_ktlg_kertas"
        const val ACTION_HOME_KTLG_LOGAM = "action_home_ktlg_logam"
        const val ACTION_HOME_KTLG_PLASTIK = "action_home_ktlg_plastik"
        const val ACTION_HOME_KTLG_LAINNYA = "acition_home_ktlg_lainnya"
        const val ACTION_HOME_JUALSAMPAH = "action_home_jualsampah"
    }

    fun jualSampahOnClick() {
        action.value = ACTION_HOME_JUALSAMPAH
    }

    fun katalogKertas() {
        action.value = ACTION_HOME_KTLG_KERTAS
    }

    fun katalogLogam() {
        action.value = ACTION_HOME_KTLG_LOGAM
    }

    fun katalogPlastik() {
        action.value = ACTION_HOME_KTLG_PLASTIK
    }

    fun katalogLainnya() {
        action.value = ACTION_HOME_KTLG_LAINNYA
    }

}