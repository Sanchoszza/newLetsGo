package com.android.letsgo.view

interface UploadPhotoStorageView {
    fun onUploadSuccess(str: String)

    fun onUploadError(localizedMessage: String)
}