package com.android.letsgo.view

interface AddMeasureAdsView {
    fun sendMessage(str: String)

    fun onUploadSuccess(str: String)

    fun onUploadError(str: String)
}