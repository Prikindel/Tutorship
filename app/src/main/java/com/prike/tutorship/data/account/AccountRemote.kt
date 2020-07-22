package com.prike.tutorship.data.account

import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.domain.type.exception.Failure

interface AccountRemote {
    fun register(
        email: String,
        password: String,
        callback: (Either<Failure, None>) -> Either<Failure, None>
    )
}