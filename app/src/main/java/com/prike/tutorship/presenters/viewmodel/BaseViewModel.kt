package com.prike.tutorship.presenters.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prike.tutorship.domain.type.HandleOnce
import com.prike.tutorship.domain.type.Failure

abstract class BaseViewModel : ViewModel() {

    var failureData: MutableLiveData<HandleOnce<Failure>> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failureData.value = HandleOnce(failure)
    }
}