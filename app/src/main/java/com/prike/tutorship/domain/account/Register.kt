package com.prike.tutorship.domain.account

import com.prike.tutorship.domain.interactor.UseCase
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.domain.type.Failure
import javax.inject.Inject

class Register @Inject constructor(
    private val repository: AccountRepository
) : UseCase<None, Register.Params>() {

    override suspend fun run(params: Params): Either<Failure, None> {
        return repository.register(
            params.first_name,
            params.last_name,
            params.email,
            params.password,
            params.type,
            params.phone,
            params.birthday,
            params.sex,
            params.city
        )
    }

    data class Params(
        val first_name: String,
        val last_name:  String,
        val email:      String,
        val password:   String,
        val type:       String,
        val phone:      String,
        val birthday:   String,
        val sex:        String,
        val city:       String
    )
}