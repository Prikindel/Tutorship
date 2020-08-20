package com.prike.tutorship.domain.residence

data class CitiesEntity (
    val cities: List<CityEntity>
) {
    fun toStringList(): List<String> {
        val list = mutableListOf<String>()
        cities.forEach { list.add(it.title) }
        return list
    }
}

data class CityEntity (
    val id: String,
    val title: String
)