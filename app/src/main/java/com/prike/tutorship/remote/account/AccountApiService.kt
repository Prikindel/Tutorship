package com.prike.tutorship.remote.account

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AccountApiService @Inject constructor(
    private val auth: FirebaseAuth
) {
    companion object{
        const val PARAM_EMAIL = "email"
        const val PARAM_PASSWORD = "password"
    }

    fun register(email: String, password: String) = auth.createUserWithEmailAndPassword(email, password)

    fun login(email: String, password: String) = auth.signInWithEmailAndPassword(email, password)
}