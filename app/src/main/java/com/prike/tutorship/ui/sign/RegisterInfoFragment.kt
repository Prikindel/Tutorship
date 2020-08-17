package com.prike.tutorship.ui.sign

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.prike.tutorship.R
import kotlinx.android.synthetic.main.register_info_fragment.*

class RegisterInfoFragment : SignFragmentBase(R.layout.register_info_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        assignListenerEditText(oneNumberDay, twoNumberDay)
        assignListenerEditText(twoNumberDay, oneNumberMonth)
        assignListenerEditText(oneNumberMonth, twoNumberMonth)
        assignListenerEditText(twoNumberMonth, oneNumberYear)
        assignListenerEditText(oneNumberYear, twoNumberYear)
        assignListenerEditText(twoNumberYear, threeNumberYear)
        assignListenerEditText(threeNumberYear, fourNumberYear)
        assignListenerEditText(fourNumberYear, etCountry)

        btnMan.setOnClickListener {
            showMessage("Здесь будет переход на следующий шаг регистрации")
        }
        btnWoman.setOnClickListener {
            showMessage("Здесь будет переход на следующий шаг регистрации")
        }

        btnLogin.setOnClickListener {
            hideProgress()
            findNav(R.id.action_registerInfoFragment_to_loginFragment)
        }
    }

    private fun assignListenerEditText(editText: EditText, toEditText: View) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                // code
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // code
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p1 == 0 && p3 == 1) {
                    toEditText.requestFocus()
                }
            }

        })
    }
}