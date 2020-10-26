package com.prike.tutorship.ui.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import com.prike.tutorship.R
import com.prike.tutorship.domain.account.AccountEntity
import com.prike.tutorship.presenters.viewmodel.AccountViewModel
import com.prike.tutorship.ui.App
import com.prike.tutorship.ui.core.ext.onFailure
import com.prike.tutorship.ui.core.ext.onSuccess
import com.prike.tutorship.ui.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.datetime.*
import kotlinx.datetime.TimeZone
import kotlin.math.abs

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

    /**
     * Переводит секунды в LocalDateTime
     *
     * @param seconds - Секунды
     * @return LocalDateTime
     */
    private fun secondsToDate(seconds: Long) = Instant.fromEpochSeconds(seconds).toLocalDateTime(TimeZone.currentSystemDefault())

    /**
     * Переводит время последнего захода в статус
     *
     * @param dateTime
     */
    private fun toConnectInfo(dateTime: LocalDateTime): String {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        // Период между текущем временеи и последним посещением
        val periodTime = dateTime.toInstant(TimeZone.currentSystemDefault()).periodUntil(now.toInstant(TimeZone.currentSystemDefault()), TimeZone.UTC)

        var connect = "В сети"
        if      (now.year != dateTime.year)                 connect = "Последний вход ${dateTime.dayOfMonth} ${numberMonthToString(dateTime.monthNumber)} ${dateTime.year} года"
        else if (now.monthNumber != dateTime.monthNumber)   connect = "Последний вход ${dateTime.dayOfMonth} ${numberMonthToString(dateTime.monthNumber)}"
        else if (now.dayOfMonth - dateTime.dayOfMonth == 1) connect = "Последний вход вчера в ${dateTime.hour}:${dateTime.minute}"
        else if (now.dayOfMonth != dateTime.dayOfMonth)     connect = "Последний вход ${dateTime.dayOfMonth} ${numberMonthToString(dateTime.monthNumber)} в ${dateTime.hour}:${dateTime.minute}"
        else if (periodTime.hours == 2)                     connect = "Последний вход два часа назад"
        else if (periodTime.hours == 1)                     connect = "Последний вход час назад"
        else if (periodTime.hours > 2)                      connect = "Последний вход в ${dateTime.hour}:${dateTime.minute}"
        else if (periodTime.minutes == 1)                   connect = "Последний вход минуту назад"
        else if (periodTime.minutes == 2)                   connect = "Последний вход две минуты назад"
        else if (periodTime.minutes == 3)                   connect = "Последний вход три минуты назад"
        else if (periodTime.minutes == 4)                   connect = "Последний вход четыре минуты назад"
        else if (periodTime.minutes == 5)                   connect = "Последний вход пять минут назад"
        else if (periodTime.minutes == 6)                   connect = "Последний вход шесть минут назад"
        else if (periodTime.minutes == 7)                   connect = "Последний вход семь минут назад"
        else if (periodTime.minutes == 8)                   connect = "Последний вход восемь минут назад"
        else if (periodTime.minutes == 9)                   connect = "Последний вход девять минут назад"
        else if (periodTime.minutes >= 10)                  connect = "Последний вход ${periodTime.minutes} минут назад"
        else if (periodTime.minutes == 0
            && abs(periodTime.seconds) > 0)                 connect = "Последний вход только что"

        /*else if (now.hour - dateTime.hour == 2)             connect = "Последний вход два часа назад"
        else if (now.hour - dateTime.hour == 1)             connect = "Последний вход час назад"
        else if (now.hour > dateTime.hour)                  connect = "Последний вход в ${dateTime.hour}:${dateTime.minute}"
        else if (now.minute - dateTime.minute == 1)         connect = "Последний вход минуту назад"
        else if (now.minute - dateTime.minute == 2)         connect = "Последний вход две минуты назад"
        else if (now.minute - dateTime.minute == 3)         connect = "Последний вход три минуты назад"
        else if (now.minute - dateTime.minute == 4)         connect = "Последний вход четыре минуты назад"
        else if (now.minute - dateTime.minute == 5)         connect = "Последний вход пять минут назад"
        else if (now.minute - dateTime.minute == 6)         connect = "Последний вход шесть минут назад"
        else if (now.minute - dateTime.minute == 7)         connect = "Последний вход семь минут назад"
        else if (now.minute - dateTime.minute == 8)         connect = "Последний вход восемь минут назад"
        else if (now.minute - dateTime.minute == 9)         connect = "Последний вход девять минут назад"
        else if (now.minute - dateTime.minute >= 10)        connect = "Последний вход ${now.minute - dateTime.minute} минут назад"
        else if (now.second - dateTime.second > 0)          connect = "Последний вход только что"*/

        return connect
    }

    private fun numberMonthToString(i: Int) = when (i) {
        1 -> "января"
        2 -> "февраля"
        3 -> "марта"
        4 -> "апреля"
        5 -> "мая"
        6 -> "июня"
        7 -> "июля"
        8 -> "августа"
        9 -> "сентября"
        10 -> "октября"
        11 -> "ноября"
        12 -> "декабря"
        else -> ""
    }

    /**
     * Переводит тип в строку, информирующую о том, какой тип пользователя
     *
     * @param type
     * @return - описание типа (учитель или ученик)
     */
    private fun typeToString(type: String) = when (type) {
        "teacher" -> getString(R.string.teacher)
        "student" -> getString(R.string.student)
        else -> getString(R.string.error_type)
    }

    /**
     * Переводит строку с номером телефона в читабельный вид
     *
     * @param phone
     * @return
     */
    private fun stringToPhoneNumber(phone: String) = phone

    /**
     * Перевод даты рождения в возраст
     *
     * @param birthday
     * @return
     */
    private fun stringToUserAge(birthday: String) = birthday

    @SuppressLint("SetTextI18n")
    private fun handleAccount(account: AccountEntity?) {
        account?.let {
            logMessage(it.toString())

            fio.text            = "${it.lastName} ${it.firstName} ${it.patronymic}"
            connect.text        = toConnectInfo(secondsToDate(it.lastSeen.toLong()))
            typeUser.text       = typeToString(it.type)
            userAge.text        = stringToUserAge(it.birthday)
            phoneNumber.text    = stringToPhoneNumber(it.phone)
        }
    }

    fun logMessage(msg: String) {
        Log.i("PROFILE", msg)
    }
}