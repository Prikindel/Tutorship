package com.prike.tutorship.cache

import com.prike.tutorship.data.account.AccountCache
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.domain.type.exception.Failure
import javax.inject.Inject

/**
 * Взаимодействие с аккаунтом в БД
 */

class AccountCacheImpl @Inject constructor(
    private val prefsManager: SharedPrefsManager
) : AccountCache {
    override fun getToken(): Either<Failure, String> {
        return prefsManager.getToken()
    }

    override fun saveToken(token: String): Either<Failure, None> {
        return prefsManager.saveToken(token)
    }
}