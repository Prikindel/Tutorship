package com.prike.tutorship.ui.sign

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout
import com.prike.tutorship.R
import com.prike.tutorship.presenters.viewmodel.AccountViewModel
import com.prike.tutorship.ui.App
import com.prike.tutorship.ui.fragment.BaseFragment

open class SignFragmentBase(override val layoutId: Int) : BaseFragment() {
    override val showToolbar = false

    protected lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        accountViewModel = getViewModel()
    }

    protected fun getTextEditText(field: TextInputLayout) = field.editText?.text.toString()

    protected fun getViewModel() = (activity as SignActivity).accountViewModel

    protected fun setTextTextInput(text: String, field: TextInputLayout) = field.editText?.setText(text)

    protected fun isEmptyTextInput(field: TextInputLayout) = getTextEditText(field).isEmpty()

    protected open fun checkedEditText(field: TextInputLayout): Boolean {
        val empty = isEmptyTextInput(field)
        if (empty) {
            field.error = getString(R.string.error_field_must_not_be_empty)
        } else {
            field.error = null
        }
        return empty
    }

    protected open fun TextInputLayout.addOnTextChangedListener() {
        this.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                // Without the code
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Without the code
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkedEditText(this@addOnTextChangedListener)
            }
        })
    }
}