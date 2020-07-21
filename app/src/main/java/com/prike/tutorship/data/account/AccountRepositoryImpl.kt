package com.prike.tutorship.data.account

import com.prike.tutorship.domain.account.AccountEntity
import com.prike.tutorship.domain.account.AccountRepository
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.domain.type.exception.Failure
import com.prike.tutorship.domain.type.flatMap
import java.util.*

/**
 * Взаимодействие с аккаунтом
 */

class AccountRepositoryImpl(
    private val accountRemote: AccountRemote,
    private val accountCache: AccountCache
) : AccountRepository {
    override fun login(email: String, password: String): Either<Failure, AccountEntity> {
        TODO("Not yet implemented")
    }

    override fun logout(): Either<Failure, None> {
        TODO("Not yet implemented")
    }

    override fun register(email: String, name: String, password: String): Either<Failure, None> {
        return accountCache.getToken().flatMap {
            accountRemote.register(email, name, password, it, Calendar.getInstance().timeInMillis)
        }
    }

    override fun forgetPassword(email: String): Either<Failure, None> {
        TODO("Not yet implemented")
    }

    override fun getCurrentAccount(): Either<Failure, AccountEntity> {
        TODO("Not yet implemented")
    }

    override fun updateAccountToken(token: String): Either<Failure, None> {
        return accountCache.saveToken(token)
    }

    override fun updateAccountLastSeen(): Either<Failure, None> {
        TODO("Not yet implemented")
    }

    override fun editAccount(entity: AccountEntity): Either<Failure, AccountEntity> {
        TODO("Not yet implemented")
    }

}