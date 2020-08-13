package com.prike.tutorship.ui.sign

import android.os.Bundle
import android.view.View
import com.prike.tutorship.R
import com.prike.tutorship.presenters.viewmodel.AccountViewModel
import com.prike.tutorship.ui.App
import com.prike.tutorship.ui.core.ext.onFailure
import com.prike.tutorship.ui.fragment.BaseFragment
import kotlinx.android.synthetic.main.register_name_fragment.btnLogin
import kotlinx.android.synthetic.main.register_type_fragment.*

class RegisterTypeFragment : BaseFragment() {
    override val layoutId = R.layout.register_type_fragment

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

        btnTeacher.setOnClickListener {
            findNav(R.id.action_registerTypeFragment_to_registerNameFragment)
        }

        btnStudent.setOnClickListener {
            findNav(R.id.action_registerTypeFragment_to_registerNameFragment)
        }

        btnLogin.setOnClickListener {
            hideProgress()
            findNav(R.id.action_registerTypeFragment_to_loginFragment)
        }
    }
}