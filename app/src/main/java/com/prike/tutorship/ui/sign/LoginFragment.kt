package com.prike.tutorship.ui.sign

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.google.android.material.textfield.TextInputLayout
import com.prike.tutorship.R
import com.prike.tutorship.domain.account.AccountEntity
import com.prike.tutorship.ui.App
import com.prike.tutorship.ui.core.ext.onFailure
import com.prike.tutorship.ui.core.ext.onSuccess
import com.prike.tutorship.ui.fragment.BaseFragment
import com.prike.tutorship.presenters.viewmodel.AccountViewModel
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
            if(validateFieldsNotEmpty()) {
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

    private fun validateFieldsNotEmpty() = validateField(loginEmail) && validateField(loginPassword)

    private fun validateFields() {
        val email = getTextEditText(loginEmail)
        val password = getTextEditText(loginPassword)
        val emailValid = email.isEmailValid()

        if (!emailValid) {
            loginEmail.error = getString(R.string.error_email_address_not_valid)
        } else {
            loginEmail.error = null
        }

        if (emailValid) {
            login(email, password)
        }
    }

    private fun login(email: String, password: String) {
        hideSoftKeyboard()
        showProgress()
        accountViewModel.login(email, password)
    }

    private fun renderAccount(account: AccountEntity?) {
        hideProgress()
        findNav(R.id.action_loginFragment_to_appActivity)
        base { finish() }
    }
}

fun String.isEmailValid() = !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()