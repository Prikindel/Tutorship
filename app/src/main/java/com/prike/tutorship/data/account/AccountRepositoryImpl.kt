package com.prike.tutorship.data.account

import com.prike.tutorship.domain.account.AccountEntity
import com.prike.tutorship.domain.account.AccountRepository
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.domain.type.Failure
import com.prike.tutorship.domain.type.flatMap
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountRemote: AccountRemote,
    private val accountCache: AccountCache
) : AccountRepository {
    override fun register(
        first_name: String,
        last_name:  String,
        email:      String,
        password:   String,
        token:      String,
        type:       String,
        phone:      String,
        birthday:   String,
        sex:        String,
        city:       String
    ): Either<Failure, None> = accountCache.getToken().flatMap {
        accountRemote.register(first_name, last_name, email, password, token, type, phone, birthday, sex, city)
    }

    override fun login(email: String, password: String): Either<Failure, AccountEntity> = accountRemote.login(email, password)

    override fun getAccount(): Either<Failure, AccountEntity> = accountCache.getAccount()

    override fun updateAccountToken(token: String): Either<Failure, None> = accountCache.saveToken(token)

    override fun logout(): Either<Failure, None> = accountCache.logout()
}