package com.prike.tutorship.ui.timetable

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.prike.tutorship.R
import com.prike.tutorship.ui.adapter.TimetableFragmentStateAdapterForViewPager
import com.prike.tutorship.ui.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_timetable.*

class TimetableFragment : BaseFragment() {
    override val layoutId = R.layout.fragment_timetable

    override val titleToolbar = R.string.timetable_toolbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        timetable_view_pager.adapter = TimetableFragmentStateAdapterForViewPager(childFragmentManager, this.lifecycle)

        TabLayoutMediator(timetable_tab_layout, timetable_view_pager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.calendar_nav_timetable)
                }
                1 -> {
                    tab.text = getString(R.string.week_nav_timetable)
                }
                2 -> {
                    tab.text = getString(R.string.list_nav_timetable)
                }
            }
        }.attach()
    }
}