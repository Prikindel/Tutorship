package com.prike.tutorship.ui.presenters.injection

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.prike.tutorship.data.account.AccountRemote
import com.prike.tutorship.data.account.AccountRepositoryImpl
import com.prike.tutorship.domain.account.AccountRepository
import com.prike.tutorship.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(
    private val context: App
) {

    @Provides
    @Singleton
    fun provideAppContext(): Context = context

    @Provides
    @Singleton
    fun provideAccountRepository(remote: AccountRemote): AccountRepository {
        return AccountRepositoryImpl(remote)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth
}