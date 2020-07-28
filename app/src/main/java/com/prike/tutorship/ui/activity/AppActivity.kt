package com.prike.tutorship.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.prike.tutorship.R
import kotlinx.android.synthetic.main.activity_app.*

class AppActivity : BaseActivity() {
    override val contentId = R.layout.activity_app

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //bottom_nav_view.setupWithNavController(findNavController(contentId))
        //NavigationUI.setupWithNavController(bottom_nav_view, findNavController(R.layout.fragment_register))
    }
}