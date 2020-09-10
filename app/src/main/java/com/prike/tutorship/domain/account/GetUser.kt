package com.prike.tutorship.domain.account

import com.prike.tutorship.domain.interactor.UseCase
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.Failure
import javax.inject.Inject

class GetUser @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<AccountEntity, GetUser.Params>() {

    override suspend fun run(params: Params): Either<Failure, AccountEntity> = accountRepository.getUser(params.id, params.email)

    data class Params(val id: String = "", val email: String = "")
}