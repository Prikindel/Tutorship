package com.prike.tutorship.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.prike.tutorship.R
import com.prike.tutorship.domain.type.Failure
import com.prike.tutorship.ui.activity.BaseActivity
import com.prike.tutorship.ui.activity.base
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    abstract val layoutId: Int

    open val titleToolbar = R.string.app_name
    open val showToolbar = true

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onResume() {
        super.onResume()

        base {
            if (showToolbar) supportActionBar?.show() else supportActionBar?.hide()
            supportActionBar?.title = getString(titleToolbar)
        }
    }

    open fun onBackPressed() {}

    fun showProgress() = base { showProgress() }

    fun hideProgress() = base { hideProgress() }

    fun hideSoftKeyboard() = base { hideSoftKeyboard() }

    fun showSoftKeyboard() = base { showSoftKeyboard() }

    open fun handleFailure(failure: Failure?) = base { handleFailure(failure) }

    fun showMessage(message: String) = base { showMessage(message) }

    protected fun findNav(fragment: Int) {
        findNavController().navigate(fragment)
    }

    inline fun base(block: BaseActivity.() -> Unit) {
        activity.base(block)
    }


    inline fun <reified T : ViewModel> viewModel(body: T.() -> Unit): T {
        val vm = ViewModelProviders.of(this, viewModelFactory)[T::class.java]
        vm.body()
        return vm
    }

    fun close() = fragmentManager?.popBackStack()
}