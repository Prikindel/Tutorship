package com.prike.tutorship.ui.sign

import android.os.Bundle
import android.view.View
import com.google.android.material.textfield.TextInputLayout
import com.prike.tutorship.R
import com.prike.tutorship.domain.account.AccountEntity
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.ui.App
import com.prike.tutorship.ui.core.ext.onFailure
import com.prike.tutorship.ui.core.ext.onSuccess
import com.prike.tutorship.ui.fragment.BaseFragment
import com.prike.tutorship.ui.presenters.viewmodel.AccountViewModel
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : BaseFragment() {
    override val layoutId = R.layout.fragment_register
    override val titleToolbar = R.string.register
    override val showToolbar = false

    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        accountViewModel = viewModel {
            onSuccess(registerData, ::handleRegister)
            onSuccess(accountData, ::handleLogin)
            onFailure(failureData, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRegister.setOnClickListener {
            register()
        }

        btnLogin.setOnClickListener {
            hideProgress()
            findNav(R.id.action_registerFragment_to_loginFragment)
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

            accountViewModel.register(
                getTextEditText(etEmail),
                getTextEditText(etPassword)
            )
        }
    }

    private fun handleLogin(accountEntity: AccountEntity?) {
        hideProgress()
        findNav(R.id.action_registerFragment_to_loginFragment)
    }

    private fun handleRegister(none: None? = None()) {
        hideProgress()
        showMessage(getString(R.string.account_created))
    }
}

fun String.isPasswordValid() = """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#${'$'}%!\-_?&])(?=\S+${'$'}).{8,}""".toRegex().matches(this)