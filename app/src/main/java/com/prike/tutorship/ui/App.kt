package com.prike.tutorship.ui

import android.app.Application
import com.prike.tutorship.ui.activity.MainActivity
import com.prike.tutorship.ui.presenters.injection.AppModule
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
@Component(modules = [AppModule::class])
interface AppComponent {

    // activities
    fun inject(activity: MainActivity)

    // fragments

    // services

}