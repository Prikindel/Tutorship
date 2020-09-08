package com.prike.tutorship.ui.sign

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.prike.tutorship.R
import com.prike.tutorship.presenters.viewmodel.AccountViewModel
import com.prike.tutorship.ui.App
import com.prike.tutorship.ui.activity.BaseActivity
import com.prike.tutorship.ui.core.ext.onFailure
import kotlinx.android.synthetic.main.sign_layout.*

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

    fun hideProgressRegister() {
        viewsProgressRegister.visibility = View.GONE
    }

    fun showProgressRegister(numberDisplay: Float = 0f) {
        viewsProgressRegister.visibility = View.VISIBLE
        viewProgressLineRegister.apply {
            layoutParams = (layoutParams as LinearLayout.LayoutParams).apply {
                weight = numberDisplay
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun blackout(flag: Boolean) =
        if (flag)
            fragmentContainer.foreground = getDrawable(R.color.foregroundBlackoutColor)
        else
            fragmentContainer.foreground = getDrawable(R.color.foregroundWhiteColor)
}