package com.prike.tutorship.remote.service

import com.prike.tutorship.remote.account.AuthResponse
import com.prike.tutorship.remote.core.BaseResponse
import com.prike.tutorship.remote.residence.CityResponse
import com.prike.tutorship.remote.residence.CountryResponse
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    companion object {
        // Methods
        const val REGISTER                      = "register.php"
        const val LOGIN                         = "login.php"
        const val GET_COUNTRIES                  = "getCountries.php"
        const val GET_CITIES_WITHOUT_COUNTRY    = "getCitiesWithoutCountry.php"

        // Params
        const val PARAM_FIRST_NAME              = "first_name"
        const val PARAM_LAST_NAME               = "last_name"
        const val PARAM_EMAIL                   = "email"
        const val PARAM_PASSWORD                = "password"
        const val PARAM_TOKEN                   = "token"
        const val PARAM_TYPE                    = "type"
        const val PARAM_PHONE                   = "phone"
        const val PARAM_BIRTHDAY                = "birthday"
        const val PARAM_SEX                     = "sex"
        const val PARAM_CITY                    = "city"
        const val PARAM_COUNTRY                 = "country"
    }

    @FormUrlEncoded
    @POST(REGISTER)
    fun register(@FieldMap params: Map<String, String>): Call<BaseResponse>

    @FormUrlEncoded
    @POST(LOGIN)
    fun login(@FieldMap params: Map<String, String>): Call<AuthResponse>

    @POST(GET_COUNTRIES)
    fun getCountries(): Call<CountryResponse>

    @FormUrlEncoded
    @POST(GET_CITIES_WITHOUT_COUNTRY)
    fun getCities(@FieldMap params: Map<String, String>): Call<CityResponse>
}