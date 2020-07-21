package com.prike.tutorship.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.prike.tutorship.R
import com.prike.tutorship.cache.AccountCacheImpl
import com.prike.tutorship.cache.SharedPrefsManager
import com.prike.tutorship.data.account.AccountRepositoryImpl
import com.prike.tutorship.domain.account.AccountRepository
import com.prike.tutorship.domain.account.Register
import com.prike.tutorship.remote.account.AccountRemoteImpl
import com.prike.tutorship.remote.core.NetworkHandler
import com.prike.tutorship.remote.core.Request
import com.prike.tutorship.remote.service.ServiceFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPrefs = this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE)

        val accountCache = AccountCacheImpl(SharedPrefsManager(sharedPrefs))
        val accountRemote = AccountRemoteImpl(Request(NetworkHandler(this)), ServiceFactory.makeService(true))

        val accountRepository: AccountRepository = AccountRepositoryImpl(accountRemote, accountCache)

        accountCache.saveToken("12345")

        val register = Register(accountRepository)
        register(Register.Params("a@a", "abcd", "123")) {
            it.either({
                Toast.makeText(this, it.javaClass.simpleName, Toast.LENGTH_SHORT).show()
            }, {
                Toast.makeText(this, "Аккаунт создан", Toast.LENGTH_SHORT).show()
            })
        }
    }
}