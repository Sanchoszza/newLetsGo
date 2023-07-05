package com.android.letsgo.presenter

import com.android.letsgo.db.AdsData
import com.android.letsgo.utils.SPHelper
import com.android.letsgo.view.AdsForLoaderView
import com.google.firebase.database.FirebaseDatabase

class GetAddsForLoaderPresenter() {
    private var view: AdsForLoaderView? = null
    private val ads: List<AdsData> = ArrayList<AdsData>()
    private val adsLat: List<AdsData> = ArrayList<AdsData>()

//    fun GetAddsForLoaderPresenter
      constructor (view: AdsForLoaderView?) : this() {
        this.view = view
    }

    fun getAdsInfo() {
        val database = FirebaseDatabase.getInstance()
        val dataRef = database.getReference("AdsData")

    }

    private fun checkZone(lat: Double, lon: Double): Boolean {
        val result = 111.2 * Math.sqrt(
            (lon - SPHelper.getLon()) * (lon - SPHelper.getLon())
                    + (lat - SPHelper.getLat()) * Math.cos(Math.PI * lon / 180) * (lat - SPHelper.getLat()) * Math.cos(
                Math.PI * lon / 180
            )
        ) * 1000
        return if (result > 250) false else true
    }

    private fun processLarge(data: List<AdsData>) {
        println("Total data count: " + data.size)
    }
}