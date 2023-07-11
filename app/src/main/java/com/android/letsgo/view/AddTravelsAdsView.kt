package com.android.letsgo.view

interface AddTravelsAdsView {
    fun sendMessage(str: String)

    fun onUploadSuccess(str: String)

    fun onUploadError(str: String)
}