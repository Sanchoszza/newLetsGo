package com.android.letsgo.presenter

import com.android.letsgo.db.PersonData
import com.android.letsgo.utils.WriteRXFirebaseUtil
import com.android.letsgo.view.AddUsersView
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class AddUsersPresenter {
    private var view: AddUsersView? = null

    constructor (view: AddUsersView?) {
        this.view = view
    }

//    constructor()

    fun usersInBd(data: PersonData){
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("Users")

        WriteRXFirebaseUtil.setValue(ref, data)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {}
                override fun onComplete() {
                    view!!.createUser()
                }

                override fun onError(e: Throwable) {
                    e.localizedMessage
                }
            })
    }
}