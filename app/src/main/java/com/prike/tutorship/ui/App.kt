package com.prike.tutorship.ui

import android.app.Application
import com.prike.tutorship.ui.activity.AppActivity
import com.prike.tutorship.ui.activity.StartActivity
import com.prike.tutorship.ui.presenters.injection.AppModule
import com.prike.tutorship.ui.presenters.injection.CacheModule
import com.prike.tutorship.ui.presenters.injection.RemoteModule
import com.prike.tutorship.ui.presenters.injection.ViewModelModule
import com.prike.tutorship.ui.sign.LoginFragment
import com.prike.tutorship.ui.sign.RegisterFragment
import com.prike.tutorship.ui.sign.SignActivity
import dagger.Component
import javax.inject.Singleton

class App  : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this)).build()
    }
}

@Singleton
@Component(modules = [AppModule::class, RemoteModule::class, CacheModule::class, ViewModelModule::class])
interface AppComponent {

    // activities
    fun inject(activity: SignActivity)
    fun inject(activity: AppActivity)
    fun inject(activity: StartActivity)

    // fragments
    fun inject(fragment: RegisterFragment)
    fun inject(fragment: LoginFragment)
    /*fun inject(fragment: ChatFragment)
    fun inject(fragment: TimetableFragment)
    fun inject(fragment: ProfileFragment)*/

    // services

}