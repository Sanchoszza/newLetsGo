package com.android.letsgo.utils

import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class WriteRXFirebaseUtil {

    companion object {
        fun setValue(ref: DatabaseReference, value: Any?): Completable? {
            return Completable.create { emitter ->
                ref.setValue(
                    value
                ) { databaseError: DatabaseError?, databaseReference: DatabaseReference? ->
                    if (databaseError == null) {
                        emitter.onComplete()
                    } else {
                        emitter.onError(databaseError.toException())
                    }
                }
            }.subscribeOn(Schedulers.io())
        }
    }
}