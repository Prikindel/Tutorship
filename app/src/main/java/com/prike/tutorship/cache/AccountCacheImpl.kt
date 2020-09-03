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

    override fun getAccount(): Either<Failure, AccountEntity> {
        TODO("Not yet implemented")
    }

    override fun logout(): Either<Failure, None> {
        TODO("Not yet implemented")
    }
}