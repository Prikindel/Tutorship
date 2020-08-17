package com.prike.tutorship.ui.sign

import android.os.Bundle
import android.view.View
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import com.prike.tutorship.R
import com.prike.tutorship.presenters.viewmodel.AccountViewModel
import com.prike.tutorship.ui.App
import com.prike.tutorship.ui.core.ext.onFailure
import com.prike.tutorship.ui.fragment.BaseFragment
import kotlinx.android.synthetic.main.register_name_fragment.*

class RegisterInfoFragment : SignFragmentBase(R.layout.register_info_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNextStep.setOnClickListener {
            showMessage("Здесь будет переход на следующий шаг регистрации")
            val builder = MaterialDatePicker.Builder.datePicker()
            val picker = builder.build()
            picker.showNow(this.parentFragmentManager, "123")
        }

        btnLogin.setOnClickListener {
            hideProgress()
            findNav(R.id.action_registerInfoFragment_to_loginFragment)
        }
    }
}