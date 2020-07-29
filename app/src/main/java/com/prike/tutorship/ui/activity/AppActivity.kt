package com.prike.tutorship.ui.activity

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.prike.tutorship.R
import kotlinx.android.synthetic.main.activity_app.*

class AppActivity : BaseActivity() {
    override val contentId = R.layout.activity_app

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NavigationUI.setupWithNavController(bottom_nav_view, findNavController(R.id.fragmentContainer))
    }
}