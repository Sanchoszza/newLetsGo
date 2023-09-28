package com.android.letsgo.view

import com.android.letsgo.db.UserRegData
import retrofit2.http.GET

interface PersonService {
    @GET("profile_data")
    suspend fun getProfile(): List<UserRegData>
}