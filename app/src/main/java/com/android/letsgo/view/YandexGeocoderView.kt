package com.android.letsgo.view

interface YandexGeocoderView {
    fun addressText(address: List<String>)

    fun messageError(str: String)
}