package com.prike.tutorship.ui.presenters.injection

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.prike.tutorship.data.account.AccountRemote
import com.prike.tutorship.data.account.AccountRepositoryImpl
import com.prike.tutorship.domain.account.AccountRepository
import com.prike.tutorship.domain.account.Authenticator
import com.prike.tutorship.ui.App
import com.prike.tutorship.ui.core.navigation.Navigator
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

    @Provides
    @Singleton
    fun provideAuthenticator(auth: FirebaseAuth): Authenticator = Authenticator(auth)

    @Provides
    @Singleton
    fun provideNavigator(authenticator: Authenticator): Navigator = Navigator(authenticator)
}