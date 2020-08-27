package com.prike.tutorship.ui.sign

import android.os.Bundle
import android.view.View
import com.prike.tutorship.R
import kotlinx.android.synthetic.main.register_email_fragment.*

class RegisterEmailFragment : SignFragmentBase(R.layout.register_email_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgressRegister(5f)

        btnRegister.setOnClickListener {
            findNav(R.id.action_registerEmailFragment2_to_loginFragment)
        }

        btnLogin.setOnClickListener {
            hideSoftKeyboard()
            findNav(R.id.action_registerEmailFragment2_to_loginFragment)
        }
    }
}