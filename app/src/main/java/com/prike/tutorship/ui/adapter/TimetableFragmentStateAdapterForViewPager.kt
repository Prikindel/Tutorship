package com.prike.tutorship.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.prike.tutorship.ui.timetable.calendar.TimetableCalendarFragment
import com.prike.tutorship.ui.timetable.listPeople.TimetableListPeopleFragment
import com.prike.tutorship.ui.timetable.week.TimetableWeekFragment

class TimetableFragmentStateAdapterForViewPager(fm: FragmentManager, lc: Lifecycle) : FragmentStateAdapter(fm, lc) {

    private val pages = listOf("calendar", "week", "list")

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> TimetableCalendarFragment()
        1 -> TimetableWeekFragment()
        2 -> TimetableListPeopleFragment()
        else -> TimetableWeekFragment()
    }

    override fun getItemCount(): Int = pages.size
}