package com.prike.tutorship.remote.account

import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.AuthResult
import com.prike.tutorship.data.account.AccountRemote
import com.prike.tutorship.domain.account.AccountEntity
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.Failure
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.remote.core.Request
import javax.inject.Inject

class AccountRemoteImpl @Inject constructor(
    private val request: Request,
    private val service: AccountApiService
) : AccountRemote {
    override fun register(
        email: String,
        password: String,
        name: String
    ): Either<Failure, None> {
        return request.make(service.register(email, password)) {
            service.updateProfile(it, name)?.let { it1 -> {
                    Tasks.await(it1)
                    it.user?.sendEmailVerification()
                }
            }

            None()
        }
    }

    override fun login(
        email: String,
        password: String
    ): Either<Failure, AccountEntity> = request.make(service.login(email, password), ::firebaseUserToAccountEntity)

    private fun firebaseUserToAccountEntity(result: AuthResult): AccountEntity {
        val user = result.user!!
        return AccountEntity(user.uid, user.displayName ?: "No name", user.email ?: "")
    }
}