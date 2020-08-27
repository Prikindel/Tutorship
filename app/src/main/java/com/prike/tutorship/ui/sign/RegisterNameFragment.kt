package com.prike.tutorship.ui.sign

import android.os.Bundle
import android.view.View
import com.prike.tutorship.R
import kotlinx.android.synthetic.main.register_name_fragment.*
import kotlinx.android.synthetic.main.sign_layout.*

class RegisterNameFragment : SignFragmentBase(R.layout.register_name_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgressRegister(1f)

        setTextTextInput(accountViewModel.getAccountRegister()?.firstName ?: "", etName)
        setTextTextInput(accountViewModel.getAccountRegister()?.lastName  ?: "", etLastName)

        etName.requestFocus()
        //showSoftKeyboard()

        etName.addOnTextChangedListener()
        etLastName.addOnTextChangedListener()

        btnNextStep.setOnClickListener {
            if (!checkFieldsOnEmpty()) {
                hideSoftKeyboard()
                accountViewModel.nameRegister(getTextEditText(etName), getTextEditText(etLastName))
                findNav(R.id.action_registerNameFragment_to_registerTypeFragment)
            }
        }

        btnLogin.setOnClickListener {
            hideSoftKeyboard()
            findNav(R.id.action_registerNameFragment_to_loginFragment)
        }
    }

    private fun checkFieldsOnEmpty() = checkedEditText(etName) || checkedEditText(etLastName)
}