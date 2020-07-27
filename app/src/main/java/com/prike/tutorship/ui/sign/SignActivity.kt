package com.prike.tutorship.ui.sign

import android.util.Log
import com.prike.tutorship.R
import com.prike.tutorship.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.sign_layout.*

class SignActivity : BaseActivity() {
    override val contentId = R.layout.sign_layout

    override fun progressStatus(viewStatus: Int) {
        progress_bar_load.visibility = viewStatus
    }

    override fun showProgress() {
        Log.i("TAG", "SHOW")
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