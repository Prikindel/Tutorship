package com.prike.tutorship.data.account

import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.domain.type.Failure

interface AccountRemote {
    fun register(
        email: String,
        password: String
    ): Either<Failure, None>
}