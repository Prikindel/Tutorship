package com.prike.tutorship.cache.account

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.prike.tutorship.data.account.AccountCache
import com.prike.tutorship.domain.account.AccountEntity
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.Failure
import com.prike.tutorship.domain.type.None
import javax.inject.Inject

class AccountCacheImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AccountCache {
    override fun getAccount(): Either<Failure, AccountEntity> {
        val user = auth.currentUser
        when (user) {
            is FirebaseUser -> return Either.Right(firebaseUserToAccountEntity(user))
        }
        return Either.Left(Failure.NoSavesAccountsError)
    }

    override fun logout(): Either<Failure, None> {
        auth.signOut()
        return Either.Right(None())
    }

    private fun firebaseUserToAccountEntity(user: FirebaseUser): AccountEntity {
        return AccountEntity(user.uid, user.displayName?:"No name", user.email ?: "")
    }
}