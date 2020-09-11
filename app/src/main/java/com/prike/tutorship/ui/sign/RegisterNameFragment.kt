package com.prike.tutorship.ui.sign

import android.os.Bundle
import android.view.View
import com.prike.tutorship.R
import kotlinx.android.synthetic.main.register_name_fragment.*

class RegisterNameFragment : SignFragmentBase(R.layout.register_name_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgressRegister(1f)

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val fname = accountViewModel.getAccountRegister()?.firstName    ?: ""
        val lname = accountViewModel.getAccountRegister()?.lastName     ?: ""

        if (!fname.isEmpty()) setTextTextInput(fname, etName)
        if (!lname.isEmpty()) setTextTextInput(lname, etLastName)
    }

    private fun checkFieldsOnEmpty() = checkedEditText(etName) || checkedEditText(etLastName)

    override fun onDestroyView() {
        super.onDestroyView()
        accountViewModel.nameRegister(getTextEditText(etName), getTextEditText(etLastName))
    }
}