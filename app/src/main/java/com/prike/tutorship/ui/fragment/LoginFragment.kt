package com.prike.tutorship.ui.fragment

import android.os.Bundle
import android.text.TextUtils
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
        showMessage(getString(R.string.account_login))
    }
}

fun String.isEmailValid() = !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isPasswordValid() = """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#${'$'}%!\-_?&])(?=\S+${'$'}).{8,}""".toRegex().matches(this)