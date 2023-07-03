package com.android.letsgo.utils

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers


class ReadRXFirebaseUtilKt {

    fun observeValueEvent(ref: CollectionReference): Observable<QuerySnapshot> {
        return Observable.create { emitter ->
            val listenerRegistration = ref.addSnapshotListener(EventListener<QuerySnapshot> { value, error ->
                if (error != null) {
                    emitter.onError(error)
                } else {
                    if (value != null) {
                        emitter.onNext(value)
                    }
                }
            })

            emitter.setCancellable {
                listenerRegistration.remove()
            }
        }.subscribeOn(Schedulers.io())
    }

    private fun setRxJavaErrorHandler() {
        RxJavaPlugins.setErrorHandler { throwable -> throwable.printStackTrace() }
    }
}
