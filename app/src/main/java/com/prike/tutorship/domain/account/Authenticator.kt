package com.prike.tutorship.domain.account

import com.prike.tutorship.data.account.AccountCache
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Authenticator @Inject constructor(
    val accountCache: AccountCache
) {
    fun userLoggedIn() = accountCache.getAccount()

    fun logout() = accountCache.logout()
}