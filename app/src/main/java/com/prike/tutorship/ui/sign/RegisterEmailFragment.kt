package com.prike.tutorship.ui.sign

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.google.android.material.textfield.TextInputLayout
import com.prike.tutorship.R
import com.prike.tutorship.domain.account.AccountEntity
import com.prike.tutorship.domain.type.None
import com.prike.tutorship.presenters.viewmodel.AccountViewModel
import com.prike.tutorship.ui.App
import com.prike.tutorship.ui.core.ext.onFailure
import com.prike.tutorship.ui.core.ext.onSuccess
import kotlinx.android.synthetic.main.register_email_fragment.*

class RegisterEmailFragment : SignFragmentBase(R.layout.register_email_fragment) {

    private lateinit var thisAccountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        thisAccountViewModel = viewModel {
            onSuccess(registerData, ::handleRegister)
            onSuccess(accountData, ::handleLogin)
            onFailure(failureData, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgressRegister(5f)

        btnRegister.setOnClickListener {
            register()
        }

        btnLogin.setOnClickListener {
            hideSoftKeyboard()
            hideProgress()
            findNav(R.id.action_registerEmailFragment2_to_loginFragment)
        }
    }

    /**
     * Проверяет переданное поле на пустоту
     *
     * @param field - TextInputLayout (поле ввода данных)
     * @return Boolean - резуьтат проверки
     */
    private fun validateField(field: TextInputLayout) =
        if (getTextEditText(field).isEmpty()) {
            field.showHelper(getString(R.string.error_field_must_not_be_empty))
            false
        } else {
            field.hideHelper()
            true
        }

    /**
     * Проверяет переданные поля ввода на валидность.
     *
     * @param allFields - Список полей ввода
     * @return Boolean - результат проверки
     */
    private fun validateFieldsNotEmpty(allFields: List<TextInputLayout>): Boolean {
        var allValid = true
        for (field in allFields) {
            allValid = (
                    if (validateField(field))           // Проверка на наличие введнных данных
                        validateFieldsData(field)       // Проверка на правильность введенных данных
                    else
                        false
                    )
                    && allValid
        }
        return allValid
    }

    /**
     * Проверка правильности введенных данных в переданном поле.
     * Вывод информации о некоректности данных
     *
     * @param field - поле ввода
     * @return Boolean - результат проверки
     */
    private fun validateFieldsData(field: TextInputLayout): Boolean {
        var errorMessage = ""
        val valid = when (field.id) {
            R.id.etEmail -> {
                errorMessage = getString(R.string.error_email_address_not_valid)
                getTextEditText(field).isEmailValid()
            }
            R.id.etPassword -> {
                errorMessage = getString(R.string.error_password_symbols)
                getTextEditText(field).isPasswordValid()
            }
            else -> true
        }

        if (!valid)
            field.showHelper(errorMessage)
        else
            field.hideHelper()

        return valid
    }

    /**
     * Создает список из полей ввода данных и отправляет их на проверку валидности.
     *
     * @return Boolean - результат проверки
     */
    private fun validateFields(): Boolean = validateFieldsNotEmpty(listOf(etEmail, etPassword, etReplPassword)) && validatePasswords()

    /**
     * Сравнивает пароли между собой
     *
     * @return Boolean - сравнение
     */
    private fun validatePasswords(): Boolean {
        val valid = getTextEditText(etPassword) == getTextEditText(etReplPassword)
        if (!valid) {
            etReplPassword.showHelper(getString(R.string.error_password_mismatch))
        }
        return valid
    }

    /**
     * Метод регистрации.
     * Проверяет поля на валидность и вызывает метод регистрации
     *
     */
    private fun register() {
        hideSoftKeyboard()

        if (validateFields()) {
            showProgress()

            accountViewModel.emailRegister(getTextEditText(etEmail))

            thisAccountViewModel.register(
                accountViewModel.getAccountRegister()!!,
                getTextEditText(etPassword)
            )
        }
    }

    private fun handleLogin(accountEntity: AccountEntity?) {
        hideProgress()
        findNav(R.id.action_registerEmailFragment2_to_loginFragment)
    }

    private fun handleRegister(none: None? = None()) {
        hideProgress()
        findNav(R.id.action_registerEmailFragment2_to_loginFragment)
    }
}

// TextInputLayout helper text
fun TextInputLayout.showHelper(message: String) {
    isHelperTextEnabled = true
    helperText = message
}

fun TextInputLayout.hideHelper() {
    isHelperTextEnabled = false
}

// Validations strings
fun String.isEmailValid() = !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
fun String.isPasswordValid() = """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#${'$'}%!\-_?&])(?=\S+${'$'}).{8,}""".toRegex().matches(this)