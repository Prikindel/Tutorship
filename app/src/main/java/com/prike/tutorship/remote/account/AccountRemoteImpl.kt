package com.prike.tutorship.remote.account

import com.google.firebase.auth.FirebaseAuth
import com.prike.tutorship.data.account.AccountRemote
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.domain.type.exception.Failure
import com.prike.tutorship.remote.core.Request
import com.prike.tutorship.remote.service.ApiService
import javax.inject.Inject

/**
 * Взаимодействие с аккаунтом сервера
 */

class AccountRemoteImpl @Inject constructor(
    private val request: Request,
    private val service: ApiService,
    private val auth: FirebaseAuth
) : AccountRemote {
    override fun register(
        email: String,
        name: String,
        password: String,
        token: String,
        userDate: Long
    ): Either<Failure, None> //= request.make(service.register(createRegisterMap(email, name, password, token, userDate))) { None() }
    {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCanceledListener {

            }
    }

    private fun createRegisterMap(
        email: String,
        name: String,
        password: String,
        token: String,
        userDate: Long
    ): Map<String, String> = HashMap<String, String>().apply {
            put(ApiService.PARAM_EMAIL, email)
            put(ApiService.PARAM_NAME, name)
            put(ApiService.PARAM_PASSWORD, password)
            put(ApiService.PARAM_TOKEN, token)
            put(ApiService.PARAM_USER_DATE, userDate.toString())
        }

}