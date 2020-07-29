package com.prike.tutorship.ui.presenters.viewmodel

import androidx.lifecycle.MutableLiveData
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

    fun register(email: String, password: String, name: String) {
        registerUseCase(Register.Params(email, password, name)) { it.either(::handleFailure, ::handleRegister) }
    }

    fun login(email: String, password: String) {
        loginUseCase(Login.Params(email, password)) {
            it.either(::handleFailure, ::handleAccount)
        }
    }

    fun getAccount() = getAccount(None()) { it.either(::handleFailure, ::handleAccount) }

    fun logout() = logout(None()) { it.either(::handleFailure, ::handleLogout) }

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