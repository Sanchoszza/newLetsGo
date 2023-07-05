package com.android.letsgo.utils

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableEmitter
import io.reactivex.rxjava3.schedulers.Schedulers


class UploadPhotoStorageUtil {

    companion object {
        private var storage: FirebaseStorage? = null
        private var storageReference: StorageReference? = null

        fun uploadPhotoStorageUtil(){
            storage = FirebaseStorage.getInstance()
            storageReference =storage?.getReference()
        }

        fun uploadPhoto(image: Uri, fileName: String?): Completable? {

            val photoref: StorageReference = UploadPhotoStorageUtil.storageReference!!.child(fileName.toString())
            return Completable.create { emmiter: CompletableEmitter ->
                val uploadTask = photoref.putFile(image)
                uploadTask.addOnCompleteListener { taskSnapshot: Task<UploadTask.TaskSnapshot?> ->
                    if (taskSnapshot.isSuccessful) {
                        photoref.downloadUrl
                            .addOnSuccessListener { uri: Uri? ->
                                SPHelper.setUrlPhoto(
                                    image.toString()
                                )
                            }.addOnFailureListener { e: Exception? -> }
                        emmiter.onComplete()
                    } else emmiter.onError(taskSnapshot.exception!!)
                }
            }.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
        }
    }
}