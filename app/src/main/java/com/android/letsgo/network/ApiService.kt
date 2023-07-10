package com.android.letsgo.network

import com.android.letsgo.db.AdsData
import com.android.letsgo.network.response.GeocoderResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable


interface ApiService {
    @GET
    fun getAds(@Query("session") session: String?): Observable<AdsData?>?

    @GET
    fun getAddress(
        @Query("format") format: String?,
        @Query("apikey") key: String?,
        @Query("geocode") geocode: String?
    ): Observable<GeocoderResponse?>?
}