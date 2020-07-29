package com.prike.tutorship.data.account

import com.prike.tutorship.domain.account.AccountEntity
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.Failure
import com.prike.tutorship.domain.type.None

interface AccountCache {
    fun getAccount(): Either<Failure, AccountEntity>
    fun logout(): Either<Failure, None>
}