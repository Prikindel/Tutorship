package com.prike.tutorship.data.residence

import com.prike.tutorship.domain.residence.CitiesEntity
import com.prike.tutorship.domain.residence.CountriesEntity
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.Failure

interface ResidenceRemote {
    fun getCounties(): Either<Failure, CountriesEntity>
    fun getCities(country: String): Either<Failure, CitiesEntity>
}