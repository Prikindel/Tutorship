package com.prike.tutorship.ui.sign

import android.os.Bundle
import android.view.View
import com.prike.tutorship.R
import kotlinx.android.synthetic.main.register_name_fragment.btnLogin
import kotlinx.android.synthetic.main.register_type_fragment.*

class RegisterTypeFragment : SignFragmentBase(R.layout.register_type_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView.text = generateHead()

        btnTeacher.setOnClickListener {
            accountViewModel.typeRegister("teacher")
            findNav(R.id.action_registerTypeFragment_to_registerInfoFragment)
        }

        btnStudent.setOnClickListener {
            accountViewModel.typeRegister("student")
            findNav(R.id.action_registerTypeFragment_to_registerInfoFragment)
        }

        btnLogin.setOnClickListener {
            findNav(R.id.action_registerTypeFragment_to_loginFragment)
        }
    }

    private fun generateHead() = getString(R.string.hello_info) + " " + accountViewModel.accountRegisterData.value?.firstName + "! \n" + getString(R.string.what_is_your_type)
}