package com.prike.tutorship.presenters.viewmodel

import androidx.lifecycle.MutableLiveData
import com.prike.tutorship.R
import com.prike.tutorship.domain.account.*
import com.prike.tutorship.domain.type.None
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    val registerUseCase: Register,
    val loginUseCase: Login,
    val getAccount: GetAccount,
    val logout: Logout
) : BaseViewModel() {

    var registerData: MutableLiveData<None> = MutableLiveData()
    var accountData: MutableLiveData<AccountEntity> = MutableLiveData()
    var logoutData: MutableLiveData<None> = MutableLiveData()

    // Параметры, которые были введины или выбраны в UI layer при регистрации
    var accountRegisterData: MutableLiveData<AccountEntity> = MutableLiveData(
        AccountEntity("",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "")
    )

    fun register(
        first_name: String,
        last_name:  String,
        email:      String,
        password:   String,
        type:       String,
        phone:      String,
        birthday:   String,
        sex:        String,
        city:       String
    ) {
        registerUseCase(Register.Params(first_name, last_name, email, password, type, phone, birthday, sex, city)) { it.either(::handleFailure, ::handleRegister) }
    }

    fun login(email: String, password: String) {
        loginUseCase(Login.Params(email, password)) {
            it.either(::handleFailure, ::handleAccount)
        }
    }

    fun getAccount() = getAccount(None()) { it.either(::handleFailure, ::handleAccount) }

    fun logout() = logout(None()) { it.either(::handleFailure, ::handleLogout) }

    // Методы регистрации
    fun typeRegister(type: String) {
        this.accountRegisterData.value?.type = type
    }

    fun nameRegister(firstName: String, lastName: String) {
        this.accountRegisterData.value?.firstName = firstName
        this.accountRegisterData.value?.lastName  = lastName
    }

    fun infoRegister(birthday: String, city: String, sex: String) {
        this.accountRegisterData.value?.birthday = birthday
        this.accountRegisterData.value?.city     = city
        this.accountRegisterData.value?.sex      = sex
    }

    fun phoneRegister(phone: String) {
        this.accountRegisterData.value?.phone = phone
    }

    fun emailRegister(email: String, password: String) {
        registerUseCase(
            Register.Params(
                this.accountRegisterData.value!!.firstName,
                this.accountRegisterData.value!!.lastName,
                email,
                password,
                this.accountRegisterData.value!!.type,
                this.accountRegisterData.value!!.phone,
                this.accountRegisterData.value!!.birthday,
                this.accountRegisterData.value!!.sex,
                this.accountRegisterData.value!!.city
            )
        ) {
            it.either(
                ::handleFailure,
                ::handleRegister
            )
        }
    }

    fun getAccountRegister() = accountRegisterData.value

    private fun handleRegister(none: None) {
        this.registerData.value = none
    }

    private fun handleAccount(account: AccountEntity) {
        this.accountData.value = account
    }

    private fun handleLogout(none: None) {
        this.logoutData.value = none
    }

    override fun onCleared() {
        super.onCleared()
        registerUseCase.unsubscribe()
        loginUseCase.unsubscribe()
    }
}