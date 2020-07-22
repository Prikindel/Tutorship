package com.prike.tutorship.domain.account

import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.domain.type.exception.Failure

interface AccountRepository {
    fun register(email: String, password: String): Either<Failure, None>
}