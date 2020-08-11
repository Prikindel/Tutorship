package com.prike.tutorship.remote.account

import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.AuthResult
import com.prike.tutorship.data.account.AccountRemote
import com.prike.tutorship.domain.account.AccountEntity
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.Failure
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.remote.core.Request
import com.prike.tutorship.remote.service.ApiService
import javax.inject.Inject

class AccountRemoteImpl @Inject constructor(
    private val request: Request,
    private val service: ApiService,
    private val serviceFirebase: AccountApiService
) : AccountRemote {
    override fun register(
        first_name: String,
        last_name:  String,
        email:      String,
        password:   String,
        token:      String,
        type:       String,
        phone:      String,
        birthday:   String,
        sex:        String,
        city:       String
    ): Either<Failure, None> = request.make(service.register(createRagisterMap(first_name, last_name, email, password, token, type, phone, birthday, sex, city))) { None() }

    override fun login(
        email: String,
        password: String
    ): Either<Failure, AccountEntity> = request.make(serviceFirebase.login(email, password), ::firebaseUserToAccountEntity)

    private fun firebaseUserToAccountEntity(result: AuthResult): AccountEntity {
        val user = result.user!!
        return AccountEntity(user.uid, user.displayName ?: "No name", user.email ?: "")
    }

    private fun createRagisterMap(
        first_name: String,
        last_name:  String,
        email:      String,
        password:   String,
        token:      String,
        type:       String,
        phone:      String,
        birthday:   String,
        sex:        String,
        city:       String
    ): Map<String, String> = HashMap<String, String>().apply {
        put(ApiService.PARAM_FIRST_NAME,    first_name)
        put(ApiService.PARAM_LAST_NAME,     last_name)
        put(ApiService.PARAM_EMAIL,         email)
        put(ApiService.PARAM_PASSWORD,      password)
        put(ApiService.PARAM_TOKEN,         token)
        put(ApiService.PARAM_TYPE,          type)
        put(ApiService.PARAM_PHONE,         phone)
        put(ApiService.PARAM_BIRTHDAY,      birthday)
        put(ApiService.PARAM_SEX,           sex)
        put(ApiService.PARAM_CITY,          city)
    }
}