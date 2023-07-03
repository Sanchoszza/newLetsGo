package com.android.letsgo.view

import com.android.letsgo.db.MeasureData

interface GetDataView {
    fun errorMessage(str: String)

    fun getInfoAds(data: List<MeasureData>)
}