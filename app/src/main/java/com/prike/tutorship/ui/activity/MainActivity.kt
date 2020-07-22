package com.prike.tutorship.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.prike.tutorship.R
import com.prike.tutorship.domain.account.AccountRepository
import com.prike.tutorship.domain.account.Register
import com.prike.tutorship.ui.App
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var registerUseCase: Register

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.appComponent.inject(this)

        registerUseCase(Register.Params("t1@ya.ru", "123456")) {Log.i("TAG", "Main GOOOOD")}
    }
}