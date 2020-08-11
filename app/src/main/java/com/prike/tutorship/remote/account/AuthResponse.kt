package com.prike.tutorship.remote.account

import com.prike.tutorship.domain.account.AccountEntity
import com.prike.tutorship.remote.core.BaseResponse

class AuthResponse (
    success: Int,
    message: String,
    val user: AccountEntity
) : BaseResponse(success, message)