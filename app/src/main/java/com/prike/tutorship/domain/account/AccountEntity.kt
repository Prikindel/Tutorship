package com.prike.tutorship.domain.account

import com.google.gson.annotations.SerializedName

data class AccountEntity(
    var id:             String,
    @SerializedName("first_name")
    var firstName:      String,
    @SerializedName("last_name")
    var lastName:       String,
    var patronymic:     String,
    var email:          String,
    var token:          String,
    var type:           String,
    var phone:          String,
    var image:          String,
    var birthday:       String,
    var sex:            String,
    var description:    String,
    var city:           String,
    @SerializedName("user_date")
    var userDate:       String,
    @SerializedName("last_seen")
    var lastSeen:       String
)