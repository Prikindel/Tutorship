package com.prike.tutorship.remote.residence

import com.prike.tutorship.domain.residence.CitiesEntity
import com.prike.tutorship.remote.core.BaseResponse

class CityResponse (
    success: Int,
    message: String,
    val cities: CitiesEntity
) : BaseResponse(success, message)
