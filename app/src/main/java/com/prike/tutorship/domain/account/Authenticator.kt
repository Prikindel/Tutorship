package com.prike.tutorship.domain.account

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Authenticator @Inject constructor(
    val auth: FirebaseAuth
) {
    fun userLoggedIn() = auth.currentUser

    fun logout() = auth.signOut()
}