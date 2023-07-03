package com.android.letsgo.view

import com.android.letsgo.db.MeasureData

interface GetMeasureView {
    fun message(str: String)

    fun getAds(data: List<MeasureData>)
}