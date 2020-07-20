package com.prike.tutorship.domain.account

import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.domain.type.exception.Failure

/**
 * Интерфейс работы с аккаунтом
 */

interface AccountRepository {
    fun login(email: String, password: String): Either<Failure, AccountEntity>
    fun logout(): Either<Failure, None>
    fun register(email: String, name: String, password: String): Either<Failure, None>
    fun forgetPassword(email: String): Either<Failure, None>

    fun getCurrentAccount(): Either<Failure, AccountEntity>

    fun updateAccountToken(token: String): Either<Failure, None>
    fun updateAccountLastSeen(): Either<Failure, None>

    fun editAccount(entity: AccountEntity): Either<Failure, AccountEntity>
}