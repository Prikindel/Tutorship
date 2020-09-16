package com.prike.tutorship.data.account

import com.prike.tutorship.domain.account.AccountEntity
import com.prike.tutorship.domain.account.AccountRepository
import com.prike.tutorship.domain.type.*
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
        type:       String,
        phone:      String,
        birthday:   String,
        sex:        String,
        city:       String
    ): Either<Failure, None> = accountCache.getToken().flatMap {
        accountRemote.register(first_name, last_name, email, password, it, type, phone, birthday, sex, city)
    }

    override fun login(email: String, password: String): Either<Failure, AccountEntity> = accountCache.getToken().flatMap {
        accountRemote.login(email, password, it)
    }.onNext {
        accountCache.saveAccount(it)
    }

    override fun logout(): Either<Failure, None> = accountCache.getToken().flatMap {
        accountCache.logout()
        accountRemote.logout(it)
    }

    override fun getAccount(): Either<Failure, AccountEntity> = accountCache.getAccount()

    override fun getUser(id: String, email: String): Either<Failure, AccountEntity> {
        if (id.isNotEmpty()) return accountRemote.getUser(id)
        if (email.isNotEmpty()) return accountRemote.getUserByEmail(email)
        return Either.Left(Failure.ServerError)
    }

    override fun updateAccountToken(token: String): Either<Failure, None> {
        accountCache.saveToken(token)

        return accountCache.getAccount().flatMap {
            accountRemote.updateToken(it.id, token, it.token)
        }
    }

    override fun checkForExist(field: String, value: String): Either<Failure, None> = accountRemote.checkForExist(field, value)
}