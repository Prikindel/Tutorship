package com.prike.tutorship.remote.account

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.prike.tutorship.data.account.AccountRemote
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.domain.type.exception.Failure
import javax.inject.Inject

class AccountRemoteImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AccountRemote {
    override fun register(email: String, password: String, callback: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if (it.isSuccessful) {
                    callback()
                } else {
                    Log.w("REG", it.exception)
                }
            }

    }
}