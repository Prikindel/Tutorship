package com.prike.tutorship.domain.account

import com.prike.tutorship.domain.interactor.UseCase
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.Failure
import com.prike.tutorship.domain.type.None
import javax.inject.Inject

class CheckForExist @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<None, CheckForExist.Params>() {
    override suspend fun run(params: Params): Either<Failure, None> = accountRepository.checkForExist(params.field, params.value)

    data class Params(val field: String, val value: String)
}