package com.prike.tutorship.ui.sign

import android.os.Bundle
import android.view.View
import com.prike.tutorship.R
import kotlinx.android.synthetic.main.register_phone_fragment.*

class RegisterPhoneFragment : SignFragmentBase(R.layout.register_phone_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgressRegister(4f)

        //setTextTextInput(accountViewModel.getAccountRegister()?.firstName ?: "", etName)

        etPhoneCode.requestFocus()
        //showSoftKeyboard()

        btnNextStep.setOnClickListener {
            findNav(R.id.action_registerPhoneFragment_to_registerEmailFragment2)
        }

        btnLogin.setOnClickListener {
            hideSoftKeyboard()
            findNav(R.id.action_registerPhoneFragment_to_loginFragment)
        }
    }
}