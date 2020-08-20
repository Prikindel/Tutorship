package com.prike.tutorship.presenters.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prike.tutorship.presenters.viewmodel.AccountViewModel
import com.prike.tutorship.presenters.viewmodel.ResidenceViewModel
import com.prike.tutorship.presenters.viewmodel.ViewModelFactory
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

    @Binds
    @IntoMap
    @ViewModelKey(ResidenceViewModel::class)
    abstract fun bindResidenceViewModel(residenceViewModel: ResidenceViewModel): ViewModel
}