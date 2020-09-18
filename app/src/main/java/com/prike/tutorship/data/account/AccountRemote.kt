package com.prike.tutorship.data.account

import com.prike.tutorship.domain.account.AccountEntity
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.domain.type.Failure

interface AccountRemote {
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

    fun login(email: String, password: String, token: String): Either<Failure, AccountEntity>

    fun getUser(id: String): Either<Failure, AccountEntity>

    fun getUserByEmail(email: String): Either<Failure, AccountEntity>

    fun logout(token: String): Either<Failure, None>

    fun updateLastSeen(token: String, id: String): Either<Failure, String>

    fun updateToken(userId: String, token: String, oldToken: String): Either<Failure, None>

    fun checkForExist(field: String, value: String): Either<Failure, None>
}