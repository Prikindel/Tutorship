package com.prike.tutorship.domain.account

import com.prike.tutorship.domain.interactor.UseCase
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.domain.type.exception.Failure
import javax.inject.Inject

/**
 * UseCase. Обновляет токен
 */

class UpdateToken @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<None, UpdateToken.Params>() {
    override suspend fun run(params: Params) = accountRepository.updateAccountToken(params.token)

    data class Params(val token: String)
}