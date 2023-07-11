package com.android.letsgo.presenter

import android.annotation.SuppressLint
import android.net.Uri
import com.android.letsgo.db.MeasureData
import com.android.letsgo.utils.UploadPhotoStorageUtil
import com.android.letsgo.utils.WriteRXFirebaseUtil
import com.android.letsgo.view.AddMeasureAdsView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class AdsMeasurePresenter {
    private var view: AddMeasureAdsView? = null

    constructor(view: AddMeasureAdsView?){
        this.view = view
    }

    fun writeData(data: MeasureData){
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val ref: DatabaseReference = database.getReference("MeasureData")

        WriteRXFirebaseUtil.setValue(ref, data)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {}
                override fun onComplete() {
                    view!!.sendMessage("Данные успешно добавлены!")
                }

                override fun onError(e: Throwable) {
                    view!!.sendMessage(e.localizedMessage)
                }
            })
    }

    @SuppressLint("CheckResult")
    fun uploadPhoto(img: Uri, file: String){
        UploadPhotoStorageUtil.uploadPhoto(img, file)?.subscribe(
            {view!!.onUploadSuccess("Фотография загружена")},
            { error -> view!!.onUploadError(error.localizedMessage) }
        )
    }
}