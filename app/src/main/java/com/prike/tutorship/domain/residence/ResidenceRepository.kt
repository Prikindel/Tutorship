package com.prike.tutorship.domain.residence

import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.Failure

interface ResidenceRepository {
    fun getCountries(): Either<Failure, CountriesEntity>
    fun getCities(country: String): Either<Failure, CitiesEntity>
}
