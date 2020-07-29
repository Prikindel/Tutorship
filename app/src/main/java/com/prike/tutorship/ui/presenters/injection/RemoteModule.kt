package com.prike.tutorship.ui.presenters.injection

import android.content.Context
import com.prike.tutorship.data.account.AccountRemote
import com.prike.tutorship.remote.account.AccountApiService
import com.prike.tutorship.remote.account.AccountRemoteImpl
import com.prike.tutorship.remote.core.NetworkHandler
import com.prike.tutorship.remote.core.Request
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

    @Singleton
    @Provides
    fun provideAccountRemote(request: Request, service: AccountApiService): AccountRemote {
        return AccountRemoteImpl(request, service)
    }
}