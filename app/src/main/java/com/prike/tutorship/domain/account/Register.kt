package com.prike.tutorship.domain.account

import com.prike.tutorship.domain.interactor.UseCase
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.domain.type.exception.Failure
import javax.inject.Inject

/**
 * UseCase. Выполняет регистрацию
 */

class Register @Inject constructor(
    private val repository: AccountRepository
) : UseCase<None, Register.Params>() {
    override suspend fun run(params: Params): Either<Failure, None> {
        return repository.register(params.email, params.name, params.password)
    }

    data class Params(val email: String, val name: String, val password: String)
}