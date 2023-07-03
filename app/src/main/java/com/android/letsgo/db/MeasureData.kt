package com.android.letsgo.db

data class MeasureData(
    var name_measure: String,
    var place_measure: String,
    var date_start_measure: String,
    var time_start_measure: String,
    var description_measure: String,
    var coast_measure: String,
    var lat: Float,
    var lon: Float,
    var imgUrl: String
)