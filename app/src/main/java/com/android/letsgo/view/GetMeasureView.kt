package com.android.letsgo.view

import com.android.letsgo.db.MeasureData

interface GetMeasureView {
    fun errorMessage(err: String?)

    fun getInfoAds(data: List<MeasureData>)
}