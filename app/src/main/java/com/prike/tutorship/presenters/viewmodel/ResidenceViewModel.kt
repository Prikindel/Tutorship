package com.prike.tutorship.presenters.viewmodel

import androidx.lifecycle.MutableLiveData
import com.prike.tutorship.domain.residence.CitiesEntity
import com.prike.tutorship.domain.residence.CountriesEntity
import com.prike.tutorship.domain.residence.GetCities
import com.prike.tutorship.domain.residence.GetCountries
import com.prike.tutorship.domain.type.None
import javax.inject.Inject

class ResidenceViewModel @Inject constructor(
    val getCountriesUseCase: GetCountries,
    val getCitiesUseCase: GetCities
) : BaseViewModel() {

    var countriesData: MutableLiveData<CountriesEntity> = MutableLiveData()
    var citiesData: MutableLiveData<CitiesEntity> = MutableLiveData()

    fun getCountriesBD() = getCountriesUseCase(None()) { it.either(::handleFailure, ::handleCounties) }

    fun getCitiesBD(country: String) = getCitiesUseCase(GetCities.Params(getCountriesData().countries.find { it.title == country }?.id ?: "")) { it.either(::handleFailure, ::handleCities) }

    fun getCountriesData() = countriesData.value ?: CountriesEntity(listOf())

    fun getCitiesData() = citiesData.value ?: CitiesEntity(listOf())

    private fun handleCounties(counties: CountriesEntity) {
        this.countriesData.value = counties
    }


    private fun handleCities(cities: CitiesEntity) {
        this.citiesData.value = cities
    }

    override fun onCleared() {
        super.onCleared()
        getCountriesUseCase.unsubscribe()
        getCitiesUseCase.unsubscribe()
    }
}