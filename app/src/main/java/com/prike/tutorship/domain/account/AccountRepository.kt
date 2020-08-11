package com.prike.tutorship.domain.account

import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.domain.type.Failure

interface AccountRepository {
    fun register(
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
        ): Either<Failure, None>
    fun login(email: String, password: String): Either<Failure, AccountEntity>
    fun logout(): Either<Failure, None>

    fun getAccount(): Either<Failure, AccountEntity>

    fun updateAccountToken(token: String): Either<Failure, None>
    //fun updateAccountLastSeen(): Either<Failure, None>
}