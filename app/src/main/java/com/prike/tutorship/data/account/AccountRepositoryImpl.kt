package com.prike.tutorship.data.account

import com.prike.tutorship.domain.account.AccountRepository
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.domain.type.exception.Failure

class AccountRepositoryImpl(
    private val accountRemote: AccountRemote
) : AccountRepository {
    override fun register(email: String, password: String): Either<Failure, None> {
        return accountRemote.register(email, password)
    }
}