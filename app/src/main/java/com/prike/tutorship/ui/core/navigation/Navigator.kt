package com.prike.tutorship.ui.core.navigation

import com.prike.tutorship.domain.account.Authenticator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor(
    private val authenticator: Authenticator
) {
    fun showMain(app: () -> Unit, login: () -> Unit) {
        val user = authenticator.userLoggedIn()
        when (user.isRight) {
            true -> app()
            false -> login()
        }
    }
}