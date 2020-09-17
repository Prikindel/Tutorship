package com.prike.tutorship.presenters.injection

import android.content.Context
import com.prike.tutorship.cache.SharedPrefsManager
import com.prike.tutorship.data.account.AccountCache
import com.prike.tutorship.data.account.AccountRemote
import com.prike.tutorship.data.account.AccountRepositoryImpl
import com.prike.tutorship.data.residence.ResidenceRemote
import com.prike.tutorship.data.residence.ResidenceRepositoryImpl
import com.prike.tutorship.domain.account.AccountRepository
import com.prike.tutorship.domain.account.Authenticator
import com.prike.tutorship.domain.residence.ResidenceRepository
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
    fun provideAccountRepository(remote: AccountRemote, cache: AccountCache): AccountRepository {
        return AccountRepositoryImpl(remote, cache)
    }

    @Provides
    @Singleton
    fun provideResidenceRepository(remote: ResidenceRemote): ResidenceRepository = ResidenceRepositoryImpl(remote)

    @Provides
    @Singleton
    fun provideAuthenticator(sharedPrefsManager: SharedPrefsManager): Authenticator = Authenticator(sharedPrefsManager)

    @Provides
    @Singleton
    fun provideNavigator(authenticator: Authenticator): Navigator = Navigator(authenticator)
}