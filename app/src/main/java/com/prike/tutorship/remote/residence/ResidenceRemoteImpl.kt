package com.prike.tutorship.remote.residence

import com.prike.tutorship.data.residence.ResidenceRemote
import com.prike.tutorship.domain.residence.CitiesEntity
import com.prike.tutorship.domain.residence.CountriesEntity
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.Failure
import com.prike.tutorship.remote.core.Request
import com.prike.tutorship.remote.service.ApiService
import javax.inject.Inject

class ResidenceRemoteImpl @Inject constructor(
    private val request: Request,
    private val service: ApiService
) : ResidenceRemote {
    override fun getCounties(): Either<Failure, CountriesEntity> = request.make(service.getCountries()) { CountriesEntity(it.countries) }

    override fun getCities(country: String): Either<Failure, CitiesEntity> = request.make(service.getCities(createCitiesMap(country))) { CitiesEntity(it.cities) }

    private fun createCitiesMap(
        country: String
    ): Map<String, String> = HashMap<String, String>().apply {
        put(ApiService.PARAM_COUNTRY, country)
    }
}