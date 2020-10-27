package com.prike.tutorship.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prike.tutorship.R
import com.prike.tutorship.secondsToDate
import kotlinx.android.synthetic.main.timetable_week_view_for_list.view.*

class TimetableWeekAdapterList(
    lessons: MutableList<Lesson>
) : BaseAdapterForRecyclerView <TimetableWeekAdapterList.Lesson, TimetableWeekAdapterList.ViewHolder>(lessons) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.timetable_week_view_for_list, parent, false)
    )

    class ViewHolder(
        view: View
    ) : BaseAdapterForRecyclerView.BaseViewHolder<Lesson>(view) {

        var lesson: Lesson = Lesson(0, 0, "", "")

        override fun bind(item: Lesson) {
            let {
                lesson = item.copy()
                view.time_lesson.text   = lesson.getTimeOfLesson()
                view.lesson_name.text   = lesson.nameLesson
                view.person_lesson.text = lesson.namePerson
            }
        }

    }

    /**
     * Класс данных для отображения в текущем списке
     *
     * @property timeStart время начала занятия в секундах
     * @property lengthOfTimeLesson продолжительность занятия в секундах
     * @property nameLesson название предмета
     * @property namePerson имя человека, с кем проходит занятие
     */
    data class Lesson(
        val timeStart:          Long,
        val lengthOfTimeLesson: Long,
        val nameLesson:         String,
        val namePerson:         String
    ) {
        fun getTimeOfLesson(): String {
            val end = secondsToDate(timeStart + lengthOfTimeLesson)
            val start = secondsToDate(timeStart)
            return "${start.hour}:${start.minute} - ${end.hour}:${end.minute}"
        }
    }
}