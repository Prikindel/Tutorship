package com.prike.tutorship.remote.account

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AccountApiService {
    companion object{
        const val PARAM_EMAIL = "email"
        const val PARAM_PASSWORD = "password"
    }

    fun register(auth: FirebaseAuth, email: String, password: String) : Task<AuthResult> {
        return  auth.createUserWithEmailAndPassword(email, password)
            /*.addOnCompleteListener{
                if (it.isSuccessful) {
                    Log.i("REG", "success")
                } else {
                    Log.w("REG", "warning")
                }
            }*/
    }
}