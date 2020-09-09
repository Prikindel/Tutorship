package com.prike.tutorship.cache

import com.prike.tutorship.data.account.AccountCache
import com.prike.tutorship.domain.account.AccountEntity
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.Failure
import com.prike.tutorship.domain.type.None
import javax.inject.Inject

class AccountCacheImpl @Inject constructor(
    private val prefsManager: SharedPrefsManager
) : AccountCache {
    override fun getToken(): Either<Failure, String> = prefsManager.getToken()

    override fun saveToken(token: String): Either<Failure, None> = prefsManager.saveToken(token)

    override fun getAccount(): Either<Failure, AccountEntity> = prefsManager.getAccount()

    override fun saveAccount(account: AccountEntity): Either<Failure, None> = prefsManager.saveAccount(account)

    override fun logout(): Either<Failure, None> = prefsManager.removeAccount()
}