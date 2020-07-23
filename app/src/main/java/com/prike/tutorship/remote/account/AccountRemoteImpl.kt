package com.prike.tutorship.remote.account

import com.prike.tutorship.data.account.AccountRemote
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.domain.type.Failure
import com.prike.tutorship.remote.core.Request
import javax.inject.Inject

class AccountRemoteImpl @Inject constructor(
    private val request: Request,
    private val service: AccountApiService
) : AccountRemote {
    override fun register(
        email: String,
        password: String
    ): Either<Failure, None> {
        return request.make(service.register(email, password)) { None() }
    }
}