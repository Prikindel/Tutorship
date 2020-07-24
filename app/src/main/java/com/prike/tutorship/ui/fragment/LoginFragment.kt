package com.prike.tutorship.ui.fragment

import android.os.Bundle
import android.view.View
import com.google.android.material.textfield.TextInputLayout
import com.prike.tutorship.R
import com.prike.tutorship.domain.account.AccountEntity
import com.prike.tutorship.ui.App
import com.prike.tutorship.ui.presenters.viewmodel.AccountViewModel
import com.prike.tutorship.ui.core.ext.onSuccess
import com.prike.tutorship.ui.core.ext.onFailure
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment() {
    override val layoutId = R.layout.fragment_login
    override val titleToolbar = R.string.login
    override val showToolbar = false

    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        accountViewModel = viewModel {
            onSuccess(accountData, ::renderAccount)
            onFailure(failureData, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener {
            if(validateFieldsOfNull()) {
                validateFields()
            }
        }

        btnRegister.setOnClickListener {
            findNav(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun getTextEditText(field: TextInputLayout) = field.editText?.text.toString()

    private fun validateField(field: TextInputLayout) =
        if (getTextEditText(field).isEmpty()) {
            field.error = getString(R.string.error_field_must_not_be_empty)
            false
        } else {
            field.error = null
            true
        }

    private fun validateFieldsOfNull(): Boolean {
        var flag = true
        if (!validateField(loginEmail)) {
            flag = false
        }
        if (!validateField(loginPassword)) {
            flag = false
        }
        return flag
    }

    private fun validateFields() {
        hideSoftKeyboard()
        val allFields = arrayOf(getTextEditText(loginEmail), getTextEditText(loginPassword))
        var allValid = true
        /*for (field in allFields) {
            allValid = field.testValidity() && allValid
        }
        if (allValid) {
            login(etEmail.text.toString(), etPassword.text.toString())
        }*/
    }

    private fun login(email: String, password: String) {
        showProgress()
        accountViewModel.login(email, password)
    }

    private fun renderAccount(account: AccountEntity?) {
        hideProgress()
        showMessage(getString(R.string.account_login))
    }
}