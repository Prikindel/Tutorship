package com.prike.tutorship.ui.activity

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.prike.tutorship.R
import com.prike.tutorship.domain.type.Failure
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    //abstract val fragment: BaseFragment

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    open val contentId = R.layout.sign_layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentId)

        setSupportActionBar(toolbar)
        //addFragment(savedInstanceState)
    }

    /*override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(
            R.id.fragmentContainer
        ) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }*/

    /*fun addFragment(savedInstanceState: Bundle?) {
        savedInstanceState ?: supportFragmentManager.inTransaction {
            add(R.id.fragmentContainer, fragment)
        }
    }*/


    open fun showProgress() = progressStatus(View.VISIBLE)

    open fun hideProgress() = progressStatus(View.GONE)

    open fun progressStatus(viewStatus: Int) {
        toolbar_progress_bar.visibility = viewStatus
    }

    fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    fun showSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }
    }

    fun handleFailure(failure: Failure?) {
        hideProgress()
        when (failure) {
            is Failure.NetworkConnectionError -> showMessage(getString(R.string.error_network))
            is Failure.ServerError -> showMessage(getString(R.string.error_server))
            is Failure.EmailAlreadyExistError -> showMessage(getString(R.string.error_email_already_exist))
            is Failure.PhoneAlreadyExistError -> showMessage(getString(R.string.error_email_already_exist))
            is Failure.AuthError -> showMessage(getString(R.string.error_auth))
            is Failure.UserIsNotFound -> showMessage(getString(R.string.error_user_is_not_found))
        }
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    inline fun <reified T : ViewModel> viewModel(body: T.() -> Unit): T {
        val vm = ViewModelProviders.of(this, viewModelFactory)[T::class.java]
        vm.body()
        return vm
    }
}

inline fun Activity?.base(block: BaseActivity.() -> Unit) {
    (this as? BaseActivity)?.let(block)
}