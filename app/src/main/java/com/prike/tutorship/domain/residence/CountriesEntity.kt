package com.prike.tutorship.domain.residence

import com.google.gson.annotations.SerializedName

data class CountriesEntity (
    @SerializedName("countries")
    val countries: List<CountryEntity>
) {
    fun toStringList(): List<String> {
        val list = mutableListOf<String>()
        countries.forEach { list.add(it.title) }
        return list
    }
}

data class CountryEntity (
    val id: String,
    val title: String
)