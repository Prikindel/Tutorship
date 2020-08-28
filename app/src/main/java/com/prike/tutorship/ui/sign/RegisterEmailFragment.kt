package com.prike.tutorship.ui.sign

import android.os.Bundle
import android.view.View
import com.google.android.material.textfield.TextInputLayout
import com.prike.tutorship.R
import com.prike.tutorship.domain.account.AccountEntity
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.presenters.viewmodel.AccountViewModel
import com.prike.tutorship.ui.App
import com.prike.tutorship.ui.core.ext.onFailure
import com.prike.tutorship.ui.core.ext.onSuccess
import kotlinx.android.synthetic.main.register_email_fragment.*

class RegisterEmailFragment : SignFragmentBase(R.layout.register_email_fragment) {

    private lateinit var thisAccountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        thisAccountViewModel = viewModel {
            onSuccess(registerData, ::handleRegister)
            onSuccess(accountData, ::handleLogin)
            onFailure(failureData, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgressRegister(5f)

        btnRegister.setOnClickListener {
            register()
        }

        btnLogin.setOnClickListener {
            hideSoftKeyboard()
            hideProgress()
            findNav(R.id.action_registerEmailFragment2_to_loginFragment)
        }
    }

    private fun validateField(field: TextInputLayout) =
        if (getTextEditText(field).isEmpty()) {
            field.error = getString(R.string.error_field_must_not_be_empty)
            false
        } else {
            field.error = null
            true
        }

    private fun validateFieldsNotEmpty(allFields: Array<TextInputLayout>): Boolean {
        var allValid = true
        for (field in allFields) {
            allValid = (if (validateField(field)) validateFieldsData(field) else false) && allValid
        }
        return allValid
    }

    private fun validateFieldsData(field: TextInputLayout): Boolean {
        var errorMessage = ""
        val valid = when (field.id) {
            R.id.etEmail -> {
                errorMessage = getString(R.string.error_email_address_not_valid)
                getTextEditText(field).isEmailValid()
            }
            R.id.etPassword -> {
                errorMessage = getString(R.string.error_password_not_valid)
                getTextEditText(field).isPasswordValid()
            }
            else -> true
        }

        if (!valid)
            field.error = errorMessage
        else
            field.error = null

        return valid
    }

    private fun validateFields(): Boolean = validateFieldsNotEmpty(arrayOf(etEmail, etPassword, etReplPassword)) && validatePasswords()

    private fun validatePasswords(): Boolean {
        val valid = getTextEditText(etPassword) == getTextEditText(etReplPassword)
        if (!valid) {
            showMessage(getString(R.string.error_password_mismatch))
        }
        return valid
    }

    private fun register() {
        hideSoftKeyboard()

        if (validateFields()) {
            showProgress()

            accountViewModel.emailRegister(getTextEditText(etEmail))

            thisAccountViewModel.register(
                accountViewModel.getAccountRegister()!!,
                getTextEditText(etPassword)
            )
        }
    }

    private fun handleLogin(accountEntity: AccountEntity?) {
        hideProgress()
        findNav(R.id.action_registerEmailFragment2_to_loginFragment)
    }

    private fun handleRegister(none: None? = None()) {
        hideProgress()
        findNav(R.id.action_registerEmailFragment2_to_loginFragment)
    }
}

//fun String.isPasswordValid() = """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#${'$'}%!\-_?&])(?=\S+${'$'}).{8,}""".toRegex().matches(this)