package com.prike.tutorship.ui.sign

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import com.prike.tutorship.R
import com.prike.tutorship.domain.account.AccountEntity
import com.prike.tutorship.domain.residence.CitiesEntity
import com.prike.tutorship.domain.residence.CountriesEntity
import com.prike.tutorship.presenters.viewmodel.AccountViewModel
import com.prike.tutorship.presenters.viewmodel.ResidenceViewModel
import com.prike.tutorship.ui.App
import com.prike.tutorship.ui.core.ext.onFailure
import com.prike.tutorship.ui.core.ext.onSuccess
import kotlinx.android.synthetic.main.register_info_fragment.*
import java.util.*

class RegisterInfoFragment : SignFragmentBase(R.layout.register_info_fragment) {

    private lateinit var residenceViewModel: ResidenceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        residenceViewModel = viewModel {
            onSuccess(citiesData, ::renderCities)
            onSuccess(countriesData, ::renderCountries)
            onFailure(failureData, ::handleFailure)
        }

        getCountries()
        showProgress()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgressRegister(3f)

        // Прослушивание ввода даты рождения
        assignListenerEditText(oneNumberDay, twoNumberDay)
        assignListenerEditText(twoNumberDay, oneNumberMonth)
        assignListenerEditText(oneNumberMonth, twoNumberMonth)
        assignListenerEditText(twoNumberMonth, oneNumberYear)
        assignListenerEditText(oneNumberYear, twoNumberYear)
        assignListenerEditText(twoNumberYear, threeNumberYear)
        assignListenerEditText(threeNumberYear, fourNumberYear)
        assignListenerEditText(fourNumberYear, etCountry)

        etCountry.setOnItemClickListener { adapterView, view, i, l ->
            showProgress()
            residenceViewModel.getCitiesBD(adapterView.getItemAtPosition(i).toString())
            etCity.requestFocus()
        }

        etCity.setOnItemClickListener { adapterView, view, i, l ->
            hideSoftKeyboard()
            cardSex.requestFocus()
        }

        btnMan.setOnClickListener {
            findNav(R.id.action_registerInfoFragment_to_registerPhoneFragment)
        }
        btnWoman.setOnClickListener {
            findNav(R.id.action_registerInfoFragment_to_registerPhoneFragment)
        }

        btnLogin.setOnClickListener {
            findNav(R.id.action_registerInfoFragment_to_loginFragment)
        }
    }

    private fun assignListenerEditText(editText: EditText, toEditText: View, func: () -> Unit = {}) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                validationDate()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // code
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p1 == 0 && p3 == 1) {
                    toEditText.requestFocus()
                    func()
                }
            }

        })
    }

    private fun renderCities(cities: CitiesEntity?) {
        hideProgress()
        etCity.setAdapter(ArrayAdapter(requireContext(), R.layout.list_item_dropdown, cities?.toStringList() ?: listOf()))
    }

    private fun renderCountries(countries: CountriesEntity?) {
        hideProgress()
        etCountry.setAdapter(ArrayAdapter(requireContext(), R.layout.list_item_dropdown, countries?.toStringList() ?: listOf()))
    }

    protected fun getCountries() = residenceViewModel.getCountriesBD()

    protected fun getCities(country: String) = residenceViewModel.getCitiesBD(country)

    private fun errorBirthday(flag: Boolean) = if (flag) {
        birthdayError.visibility = View.VISIBLE
        switchThemeDate(resources.getColor(android.R.color.holo_red_light))
    } else {
        birthdayError.visibility = View.INVISIBLE
        switchThemeDate(resources.getColor(R.color.backgroundSignCard))
    }

    private fun switchThemeDate(color: Int) {
        oneNumberDay.background.setTint(color)
        twoNumberDay.background.setTint(color)
        oneNumberMonth.background.setTint(color)
        twoNumberMonth.background.setTint(color)
        oneNumberYear.background.setTint(color)
        twoNumberYear.background.setTint(color)
        threeNumberYear.background.setTint(color)
        fourNumberYear.background.setTint(color)
    }

    fun validationDate(): Boolean {
        if (isNotEmptyAllEditTextBirthday()) {
            if (!validateYear()) {
                errorBirthday(true)
                return false
            }
            if (!validateMonth()) {
                errorBirthday(true)
                return false
            }
            if (!validateDay()) {
                errorBirthday(true)
                return false
            }
            errorBirthday(false)
            return true
        }

        return false
    }

    fun isNotEmptyAllEditTextBirthday() = !isEmptyEditText(oneNumberDay) &&
            !isEmptyEditText(twoNumberDay) &&
            !isEmptyEditText(oneNumberMonth) &&
            !isEmptyEditText(twoNumberMonth) &&
            !isEmptyEditText(oneNumberYear) &&
            !isEmptyEditText(twoNumberYear) &&
            !isEmptyEditText(threeNumberYear) &&
            !isEmptyEditText(fourNumberYear)

    private fun isEmptyEditText(view: AppCompatEditText) = view.text?.isEmpty() ?: true

    fun validateDay() = getDay().toInt() in 1..getMaxDaysOfDate(getMonth().toInt(), getYear().toInt())

    fun validateMonth() = getMonth().toInt() in 1..12

    fun validateYear() = getYear().toInt() in 1900..Calendar.getInstance().get(Calendar.YEAR)

    private fun getDay() = oneNumberDay.text.toString() + twoNumberDay.text.toString()

    private fun getMonth() = oneNumberMonth.text.toString() + twoNumberMonth.text.toString()

    private fun getYear() = oneNumberYear.text.toString() +
            twoNumberYear.text.toString() +
            threeNumberYear.text.toString() +
            fourNumberYear.text.toString()

    private fun getMaxDaysOfDate(month: Int, year: Int): Int {
        val days = GregorianCalendar(year, month - 1, 1).getActualMaximum(Calendar.DAY_OF_MONTH)
        return days
    }

    private fun countiesItemsList() = residenceViewModel.getCountriesData().toStringList()

    private fun citiesItemsList() = residenceViewModel.getCitiesData().toStringList()

}