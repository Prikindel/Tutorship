package com.prike.tutorship.remote.residence

import com.prike.tutorship.domain.residence.CitiesEntity
import com.prike.tutorship.domain.residence.CityEntity
import com.prike.tutorship.remote.core.BaseResponse

class CityResponse (
    success: Int,
    message: String,
    val cities: List<CityEntity>
) : BaseResponse(success, message)
