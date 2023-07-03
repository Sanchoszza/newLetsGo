package com.android.letsgo.view

interface AddAdsView {
    fun sendMessage(str: String)

    fun onUploadSuccess(str: String)

    fun onUploadError(str: String)
}