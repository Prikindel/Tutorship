package com.prike.tutorship.domain.account

import com.prike.tutorship.domain.interactor.UseCase
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.Failure
import com.prike.tutorship.domain.type.None
import javax.inject.Inject

class GetAccount @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<AccountEntity, None>() {

    override suspend fun run(params: None): Either<Failure, AccountEntity> = accountRepository.getCurrentAccunt()
}