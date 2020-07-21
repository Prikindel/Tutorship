package com.prike.tutorship.remote.service

import com.prike.tutorship.remote.core.BaseResponse
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * API
 */

interface ApiService {
    companion object {
        // методы
        const val REGISTER          = ""

        // параметры
        const val PARAM_EMAIL       = "email"
        const val PARAM_PASSWORD    = "password"
        const val PARAM_NAME        = "name"
        const val PARAM_TOKEN       = "token"
        const val PARAM_USER_DATE   = "user_date"
    }

    @FormUrlEncoded
    @POST(REGISTER)
    fun register(@FieldMap params: Map<String, String>): Call<BaseResponse>
}