package com.prike.tutorship.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.prike.tutorship.R
import com.prike.tutorship.data.account.AccountRemote
import com.prike.tutorship.data.account.AccountRepositoryImpl
import com.prike.tutorship.domain.account.AccountRepository
import com.prike.tutorship.ui.App

class MainActivity : AppCompatActivity() {

    private lateinit var acr: AccountRemote

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.appComponent.inject(this)

        val a: AccountRepository = AccountRepositoryImpl(acr)
        a.register("test@ya.ru", "123")
    }
}