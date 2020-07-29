package com.prike.tutorship.data.account

import com.prike.tutorship.domain.account.AccountEntity
import com.prike.tutorship.domain.account.AccountRepository
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.domain.type.Failure
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountRemote: AccountRemote,
    private val accountCache: AccountCache
) : AccountRepository {
    override fun register(email: String, password: String): Either<Failure, None> = accountRemote.register(email, password)

    override fun login(email: String, password: String): Either<Failure, AccountEntity> = accountRemote.login(email, password)

    override fun getAccount(): Either<Failure, AccountEntity> = accountCache.getAccount()

    override fun logout(): Either<Failure, None> = accountCache.logout()
}