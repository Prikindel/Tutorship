package com.prike.tutorship.ui.sign

import android.os.Bundle
import android.view.View
import com.google.android.material.textfield.TextInputLayout
import com.prike.tutorship.R
import com.prike.tutorship.presenters.viewmodel.AccountViewModel
import com.prike.tutorship.ui.App
import com.prike.tutorship.ui.core.ext.onFailure
import com.prike.tutorship.ui.fragment.BaseFragment
import kotlinx.android.synthetic.main.register_name_fragment.*

class RegisterNameFragment : BaseFragment() {
    override val layoutId = R.layout.register_name_fragment
    override val titleToolbar = R.string.register
    override val showToolbar = false

    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        accountViewModel = viewModel {
            onFailure(failureData, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNextStep.setOnClickListener {
            accountViewModel.nameRegister(getTextEditText(etName), getTextEditText(etLastName))
            showMessage("Здесь будет переход на следующий шаг регистрации")
        }

        btnLogin.setOnClickListener {
            hideProgress()
            findNav(R.id.action_registerNameFragment_to_loginFragment)
        }
    }

    private fun getTextEditText(field: TextInputLayout) = field.editText?.text.toString()
}