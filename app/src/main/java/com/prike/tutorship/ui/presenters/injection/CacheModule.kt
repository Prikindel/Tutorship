package com.prike.tutorship.ui.presenters.injection

import com.google.firebase.auth.FirebaseAuth
import com.prike.tutorship.cache.account.AccountCacheImpl
import com.prike.tutorship.data.account.AccountCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton
    fun provideAccountCache(auth: FirebaseAuth): AccountCache = AccountCacheImpl(auth)
}