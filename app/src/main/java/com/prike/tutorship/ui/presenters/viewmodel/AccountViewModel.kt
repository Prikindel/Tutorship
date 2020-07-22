package com.prike.tutorship.ui.presenters.viewmodel

import androidx.lifecycle.MutableLiveData
import com.prike.tutorship.domain.account.Register
import com.prike.tutorship.domain.type.None
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    val registerUseCase: Register
) : BaseViewModel() {

    var registerData: MutableLiveData<None> = MutableLiveData()

    fun register(email: String, password: String) {
        registerUseCase(Register.Params(email, password)) { it.either(::handleFailure, ::handleRegister) }
    }

    private fun handleRegister(none: None) {
        this.registerData.value = none
    }

    override fun onCleared() {
        super.onCleared()
        registerUseCase.unsubscribe()
    }
}