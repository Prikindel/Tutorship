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

    var adapterList = TimetableWeekAdapterList(getRandomList())
    var day = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listLessons.layoutManager = LinearLayoutManager(context)
        listLessons.adapter = adapterList

        prevBtn.setOnClickListener {
            prevDay()
        }

        nextBtn.setOnClickListener {
            nextDay()
        }
    }

    private fun getRandomList(): MutableList<Lesson> {
        val size = (0..5).random()
        val list = arrayListOf<Lesson>()
        var start = 1603796400L
        (0 until size).forEach {
            list.add(
                Lesson(
                    start,
                    3600,
                    getRandomLesson(),
                    getRandomPerson()
                )
            )
            start += 5400L
        }
        return list
    }

    private fun getRandomLesson() = when ((0..10).random()) {
        0 -> "Математика"
        1 -> "Английский"
        2 -> "Информатика"
        3 -> "Русский"
        4 -> "Биология"
        5 -> "Физика"
        6 -> "Бокс"
        7 -> "Плавание"
        8 -> "Химия"
        9 -> "Итальянский"
        10 -> "Немецкий"
        else -> fail("Failure lesson")
    }

    private fun getRandomPerson() = when ((0..10).random()) {
        0 -> "Илья"
        1 -> "Гоша"
        2 -> "Аня"
        3 -> "Леня"
        4 -> "Саша"
        5 -> "Паша"
        6 -> "Даша"
        7 -> "Оля"
        8 -> "Катя"
        9 -> "Таня"
        10 -> "Ваня"
        else -> fail("Failure name person")
    }

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
        if (day < 7) {
            day++
            showDay(day)
            adapterList.update(getRandomList())
            adapterList.notifyDataSetChanged()
        }
    }

    /**
     * Обрабатывает нажатие кнопки предыдущего дня
     *
     */
    fun prevDay() {
        if (day > 1) {
            day--
            showDay(day)
            adapterList.update(getRandomList())
            adapterList.notifyDataSetChanged()
        }
    }

    fun updateListOfLessons(lessons: List<String>) {

    }

}