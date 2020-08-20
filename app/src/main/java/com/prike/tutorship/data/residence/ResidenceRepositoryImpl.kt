package com.prike.tutorship.data.residence

import com.prike.tutorship.domain.residence.CitiesEntity
import com.prike.tutorship.domain.residence.CountriesEntity
import com.prike.tutorship.domain.residence.ResidenceRepository
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.Failure
import javax.inject.Inject

class ResidenceRepositoryImpl @Inject constructor(
    private val remote: ResidenceRemote
) : ResidenceRepository {

    override fun getCountries(): Either<Failure, CountriesEntity> = remote.getCounties()

    override fun getCities(country: String): Either<Failure, CitiesEntity> = remote.getCities(country)
}