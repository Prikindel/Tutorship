package com.prike.tutorship.remote.account

import android.util.Log
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.prike.tutorship.data.account.AccountRemote
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.domain.type.exception.Failure
import com.prike.tutorship.remote.core.Request
import javax.inject.Inject

class AccountRemoteImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val request: Request
) : AccountRemote {
    override fun register(
        email: String,
        password: String
    ): Either<Failure, None> {
        return request.make(auth.createUserWithEmailAndPassword(email, password)) { None() }
    }
}