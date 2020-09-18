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
        const val TOKENS                        = "tokens.php"
        const val UPDATE_LAST_SEEN              = "updateLastSeen.php"
        //const val UPDATE_TOKEN                  = "updateUserToken.php"
        const val GET_COUNTRIES                 = "getCountries.php"
        const val GET_CITIES_WITHOUT_COUNTRY    = "getCitiesWithoutCountry.php"
        const val CHECK_FOR_EXIST               = "checkForExist.php"
        const val GET_ACCOUNT                   = "getUser.php"

        // Params
        const val PARAMS_API                    = "api"
        const val PARAMS_ID                     = "id"
        const val PARAMS_USER_ID                = "user_id"
        const val PARAM_FIRST_NAME              = "first_name"
        const val PARAM_LAST_NAME               = "last_name"
        const val PARAM_EMAIL                   = "email"
        const val PARAM_PASSWORD                = "password"
        const val PARAM_TOKEN                   = "token"
        const val PARAM_OLD_TOKEN               = "old_token"
        const val PARAM_TYPE                    = "type"
        const val PARAM_PHONE                   = "phone"
        const val PARAM_BIRTHDAY                = "birthday"
        const val PARAM_SEX                     = "sex"
        const val PARAM_CITY                    = "city"
        const val PARAM_COUNTRY                 = "country"
        const val PARAM_FIELD                   = "field"
        const val PARAM_VALUE                   = "value"

        // Api
        const val API_UPDATE_TOKEN              = "updateToken"
        const val API_LOGOUT                    = "logout"
    }

    @FormUrlEncoded
    @POST(REGISTER)
    fun register(@FieldMap params: Map<String, String>): Call<BaseResponse>

    @FormUrlEncoded
    @POST(LOGIN)
    fun login(@FieldMap params: Map<String, String>): Call<AuthResponse>

    @FormUrlEncoded
    @POST(TOKENS)
    fun logout(@FieldMap params: Map<String, String>): Call<BaseResponse>

    @FormUrlEncoded
    @POST(GET_ACCOUNT)
    fun getAccount(@FieldMap params: Map<String, String>): Call<AuthResponse>

    @FormUrlEncoded
    @POST(UPDATE_LAST_SEEN)
    fun updateLastSeen(@FieldMap params: Map<String, String>): Call<BaseResponse>

    @FormUrlEncoded
    @POST(TOKENS)
    fun updateToken(@FieldMap params: Map<String, String>): Call<BaseResponse>

    @POST(GET_COUNTRIES)
    fun getCountries(): Call<CountryResponse>

    @FormUrlEncoded
    @POST(GET_CITIES_WITHOUT_COUNTRY)
    fun getCities(@FieldMap params: Map<String, String>): Call<CityResponse>

    @FormUrlEncoded
    @POST(CHECK_FOR_EXIST)
    fun checkForExist(@FieldMap params: Map<String, String>): Call<BaseResponse>
}