package com.prike.tutorship.presenters.injection

import android.content.Context
import android.content.SharedPreferences
import com.prike.tutorship.cache.AccountCacheImpl
import com.prike.tutorship.cache.SharedPrefsManager
import com.prike.tutorship.data.account.AccountCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideAccountCache(prefsManager: SharedPrefsManager): AccountCache = AccountCacheImpl(prefsManager)
}