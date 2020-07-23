package com.prike.tutorship.ui.fragment

import android.os.Bundle
import android.view.View
import com.prike.tutorship.R
import com.prike.tutorship.domain.account.AccountEntity
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.ui.App
import com.prike.tutorship.ui.core.ext.*
import com.prike.tutorship.ui.presenters.viewmodel.AccountViewModel
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : BaseFragment() {
    override val layoutId = R.layout.fragment_register
    override val titleToolbar = R.string.register

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

        btnNewMembership.setOnClickListener {
            register()
        }

        btnAlreadyHaveAccount.setOnClickListener {
            hideProgress()
            findNav(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun validateFields(): Boolean {
        val allFields = arrayOf(etEmail, etPassword, etConfirmPassword)
        var allValid = true
        for (field in allFields) {
            allValid = field.testValidity() && allValid
        }
        return allValid && validatePasswords()
    }

    private fun validatePasswords(): Boolean {
        val valid = etPassword.text.toString() == etConfirmPassword.text.toString()
        if (!valid) {
            showMessage(getString(R.string.error_password_mismatch))
        }
        return valid
    }

    private fun register() {
        hideSoftKeyboard()

        val allValid = validateFields()

        if (allValid) {
            showProgress()

            accountViewModel.register(
                etEmail.text.toString(),
                etPassword.text.toString()
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