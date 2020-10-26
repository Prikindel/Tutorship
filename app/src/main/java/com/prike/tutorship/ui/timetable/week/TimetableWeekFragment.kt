package com.prike.tutorship.ui.timetable.week

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.prike.tutorship.R
import com.prike.tutorship.ui.adapter.TimetableWeekAdapterList
import kotlinx.android.synthetic.main.timetable_week.*

class TimetableWeekFragment : Fragment(R.layout.timetable_week) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listLessons.layoutManager = LinearLayoutManager(context)
        listLessons.adapter = TimetableWeekAdapterList(arrayListOf("1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5"))
    }
}