package com.prike.tutorship.ui.presenters.injection

import com.google.firebase.auth.FirebaseAuth
import com.prike.tutorship.data.account.AccountRemote
import com.prike.tutorship.remote.account.AccountRemoteImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun provideAccountRemote(auth: FirebaseAuth): AccountRemote {
        return AccountRemoteImpl(auth)
    }
}