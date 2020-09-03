package com.prike.tutorship.domain.residence

import com.prike.tutorship.domain.interactor.UseCase
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.Failure
import com.prike.tutorship.domain.type.None
import javax.inject.Inject

class GetCountries @Inject constructor(
    private val repository: ResidenceRepository
) : UseCase<CountriesEntity, None>() {

    override suspend fun run(none: None): Either<Failure, CountriesEntity> = repository.getCountries()
}