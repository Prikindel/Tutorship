package com.prike.tutorship.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.prike.tutorship.R
import com.prike.tutorship.domain.account.AccountEntity
import com.prike.tutorship.presenters.viewmodel.AccountViewModel
import com.prike.tutorship.ui.App
import com.prike.tutorship.ui.core.ext.onFailure
import com.prike.tutorship.ui.core.ext.onSuccess
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment() {
    override val layoutId = R.layout.fragment_profile

    override val titleToolbar = R.string.profile_toolbar

    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        accountViewModel = viewModel {
            onSuccess(accountData, ::handleAccount)
            onFailure(failureData, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        accountViewModel.getAccount()
    }

    @SuppressLint("SetTextI18n")
    private fun handleAccount(account: AccountEntity?) {
        account?.let {
            fio.text = "${it.firstName} ${it.lastName} ${it.patronymic}"
        }
    }
}