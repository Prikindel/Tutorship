package com.prike.tutorship.data.account

import android.util.Log
import com.prike.tutorship.domain.account.AccountRepository
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.domain.type.exception.Failure
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountRemote: AccountRemote
) : AccountRepository {
    override fun register(email: String, password: String) {
         accountRemote.register(email, password) {
            Log.i("REG", true.toString())
            return@register it
        }
    }
}