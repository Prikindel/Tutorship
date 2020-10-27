package com.prike.tutorship.ui.timetable.week

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.prike.tutorship.R
import com.prike.tutorship.fail
import com.prike.tutorship.ui.adapter.TimetableWeekAdapterList
import com.prike.tutorship.ui.adapter.TimetableWeekAdapterList.*
import kotlinx.android.synthetic.main.timetable_week.*

class TimetableWeekFragment : Fragment(R.layout.timetable_week) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listLessons.layoutManager = LinearLayoutManager(context)
        listLessons.adapter = TimetableWeekAdapterList(createList().toMutableList())

        prevBtn.setOnClickListener {
            prevDay()
        }

        nextBtn.setOnClickListener {
            nextDay()
        }
    }

    private fun createList() = listOf(
        Lesson(
            1603807200,
            3600,
            "Математика",
            "Илья"
        ),
        Lesson(
            1603812156,
            3600,
            "Математика",
            "Леня"
        ),
        Lesson(
            1603818000,
            3600,
            "Английский",
            "Гоша"
        ),
        Lesson(
            1603823400,
            3600,
            "Математика",
            "Гоша"
        ),
        Lesson(
            1603827000,
            3600,
            "Английский",
            "Аня"
        )
    )

    /**
     * Возвращает название дня недели.
     *
     * @param day номер дня недели от 1 до 7
     * @return Название дня недели
     */
    fun getNameDay(day: Int): String = when (day) {
        1 -> getString(R.string.monday)
        2 -> getString(R.string.tuesday)
        3 -> getString(R.string.wednesday)
        4 -> getString(R.string.thursday)
        5 -> getString(R.string.friday)
        6 -> getString(R.string.saturday)
        7 -> getString(R.string.sunday)
        else -> fail("Number day is not between 1 to 7")
    }

    /**
     * Отобразить переданный день недели
     *
     * @param day номер дня недели от 1 до 7
     */
    fun showDay(day: Int) {
        nameDayOfWeek.text = getNameDay(day)
    }

    /**
     * Обрабатывает нажатие кнопки следующего дня
     *
     */
    fun nextDay() {
        Log.e("TAG", "next click")
    }

    /**
     * Обрабатывает нажатие кнопки предыдущего дня
     *
     */
    fun prevDay() {
        Log.e("TAG", "prev click")
    }

    fun updateListOfLessons(lessons: List<String>) {

    }

}