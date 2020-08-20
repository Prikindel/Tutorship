package com.prike.tutorship.domain.residence

import com.prike.tutorship.domain.interactor.UseCase
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.Failure
import javax.inject.Inject

class GetCities @Inject constructor(
    private val repository: ResidenceRepository
) : UseCase<CitiesEntity, GetCities.Params>() {

    override suspend fun run(params: Params): Either<Failure, CitiesEntity> = repository.getCities(params.country)

    data class Params (val country: String)
}