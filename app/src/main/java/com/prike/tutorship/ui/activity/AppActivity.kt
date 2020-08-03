package com.prike.tutorship.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.prike.tutorship.R
import com.prike.tutorship.ui.App
import com.prike.tutorship.ui.core.ext.onFailure
import com.prike.tutorship.ui.core.ext.onSuccess
import com.prike.tutorship.ui.presenters.viewmodel.AccountViewModel
import kotlinx.android.synthetic.main.activity_app.*
import kotlinx.android.synthetic.main.navigation_account.*

class AppActivity : BaseActivity() {
    override val contentId = R.layout.activity_app

    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.appComponent.inject(this)

        accountViewModel = viewModel {
            onFailure(failureData, ::handleFailure)
        }

        NavigationUI.setupWithNavController(bottom_nav_view, findNavController(R.id.fragmentContainer))

        btnLogout.setOnClickListener {
            accountViewModel.logout()
            findNavController(R.id.fragmentContainer).navigate(R.id.action_timetable_page_to_signActivity2)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_top_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout ->
                if (drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.closeDrawer(navigationView)
                } else {
                    drawerLayout.openDrawer(navigationView)
                }
        }
        return super.onOptionsItemSelected(item)
    }
}