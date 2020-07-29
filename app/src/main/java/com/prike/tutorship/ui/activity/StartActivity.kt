package com.prike.tutorship.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.prike.tutorship.R
import com.prike.tutorship.ui.App
import com.prike.tutorship.ui.core.navigation.Navigator
import javax.inject.Inject

class StartActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)

        App.appComponent.inject(this)



        navigator.showMain({
            findNavController(R.id.fragmentContainer).navigate(R.id.action_loadAppFragment_to_appActivity)
        }, {
            findNavController(R.id.fragmentContainer).navigate(R.id.action_loadAppFragment_to_signActivity)
        })
    }
}