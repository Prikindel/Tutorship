package com.prike.tutorship.presenters.injection

import android.content.Context
import com.prike.tutorship.BuildConfig
import com.prike.tutorship.data.account.AccountRemote
import com.prike.tutorship.remote.account.AccountApiService
import com.prike.tutorship.remote.account.AccountRemoteImpl
import com.prike.tutorship.remote.core.NetworkHandler
import com.prike.tutorship.remote.core.Request
import com.prike.tutorship.remote.service.ApiService
import com.prike.tutorship.remote.service.ServiceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun provideNetworkHandler(context: Context): NetworkHandler {
        return NetworkHandler(context)
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService = ServiceFactory.makeService(BuildConfig.DEBUG)

    @Singleton
    @Provides
    fun provideAccountRemote(request: Request, service: ApiService, serviceFirebase: AccountApiService): AccountRemote {
        return AccountRemoteImpl(request, service, serviceFirebase)
    }
}