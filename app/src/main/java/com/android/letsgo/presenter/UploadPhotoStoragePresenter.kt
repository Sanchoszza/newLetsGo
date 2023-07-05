package com.android.letsgo.presenter

import android.annotation.SuppressLint
import android.net.Uri
import com.android.letsgo.utils.UploadPhotoStorageUtil
import com.android.letsgo.view.UploadPhotoStorageView

class UploadPhotoStoragePresenter {

    companion object{
        private var view: UploadPhotoStorageView? = null

        fun uploadPhotoStoragePresenter(view: UploadPhotoStorageView?) {
            this.view = view
        }

        @SuppressLint("CheckResult")
        fun uploadPhoto (img: Uri, file: String){
            UploadPhotoStorageUtil.uploadPhoto(img, file)?.subscribe(
                { view!!.onUploadSuccess("Фотография загружена") },
                { error -> view!!.onUploadError(error.localizedMessage) }
            )
        }
    }
}