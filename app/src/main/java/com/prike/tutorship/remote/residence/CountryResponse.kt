package com.prike.tutorship.remote.residence

import com.prike.tutorship.domain.residence.CountriesEntity
import com.prike.tutorship.remote.core.BaseResponse

class CountryResponse (
    success: Int,
    message: String,
    val countries: CountriesEntity
) : BaseResponse(success, message)
