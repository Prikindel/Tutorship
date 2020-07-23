package com.prike.tutorship.ui.presenters.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prike.tutorship.ui.presenters.viewmodel.AccountViewModel
import com.prike.tutorship.ui.presenters.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindAccountViewModel(accountViewModel: AccountViewModel): ViewModel
}