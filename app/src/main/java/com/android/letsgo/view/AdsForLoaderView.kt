package com.android.letsgo.view

import com.android.letsgo.db.MeasureData

interface AdsForLoaderView {
    fun getAds(dataList: List<MeasureData?>?)
}