package com.prike.tutorship.domain.account

import com.prike.tutorship.cache.SharedPrefsManager
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Для проверки авторизации пользователя
 * и выхода из аккаунта
 *
 * @property accountCache
 */
@Singleton
class Authenticator @Inject constructor(
    private val sharedPrefsManager: SharedPrefsManager
) {
    fun userLoggedIn() = sharedPrefsManager.containsAnyAccount()

    fun logout() = sharedPrefsManager.removeAccount()
}