package com.prike.tutorship.ui.sign

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.prike.tutorship.R
import com.prike.tutorship.presenters.viewmodel.AccountViewModel
import com.prike.tutorship.ui.App
import com.prike.tutorship.ui.activity.BaseActivity
import com.prike.tutorship.ui.core.ext.onFailure
import kotlinx.android.synthetic.main.activity_app.*
import kotlinx.android.synthetic.main.navigation_account.*
import kotlinx.android.synthetic.main.sign_layout.*
import kotlinx.android.synthetic.main.sign_layout.fragmentContainer

class SignActivity : BaseActivity() {
    override val contentId = R.layout.sign_layout

    lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.appComponent.inject(this)

        accountViewModel = viewModel {
            onFailure(failureData, ::handleFailure)
        }
    }

    override fun progressStatus(viewStatus: Int) {
        progress_bar_load.visibility = viewStatus
    }

    override fun showProgress() {
        blackout(true)
        super.showProgress()
    }

    override fun hideProgress() {
        blackout(false)
        super.hideProgress()
    }

    private fun blackout(flag: Boolean) =
        if (flag)
            fragmentContainer.foreground = getDrawable(R.color.foregroundBlackoutColor)
        else
            fragmentContainer.foreground = getDrawable(R.color.foregroundWhiteColor)
}