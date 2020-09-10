package com.prike.tutorship.ui.core.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.prike.tutorship.domain.account.Authenticator
import com.prike.tutorship.ui.activity.AppActivity
import com.prike.tutorship.ui.sign.SignActivity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Выполняет роль навигации при входе в приложение
 *
 * @property authenticator
 */
@Singleton
class Navigator @Inject constructor(
    private val authenticator: Authenticator
) {
    /**
     * Проверяет, авторизован ли пользователь.
     * В положительном случае запускает activity с контентом приложения.
     * В отрицательном - activity аутентификации
     *
     * @param context
     */
    fun showMain(context: Context) {
        when (authenticator.userLoggedIn()) {
            true -> showApp(context)
            false -> showLogin(context)
        }
    }

    /**
     * Запускает activity с контентом
     *
     * @param context
     * @param newTask - очищать ли backstack
     */
    fun showApp(context: Context, newTask: Boolean = true) = context.startActivity<AppActivity>(newTask = newTask)

    /**
     * Запускает activity с аутентификацией
     *
     * @param context
     * @param newTask - очищать ли backstack
     */
    fun showLogin(context: Context, newTask: Boolean = true) = context.startActivity<SignActivity>(newTask = newTask)
}

/**
 * Запускает activity, формирует Intent с необходимыми данными
 *
 * @param T - класс запускаемой activity
 * @param newTask - очищать или нет backstack
 * @param args - аргументы
 */
private inline fun <reified T> Context.startActivity(newTask: Boolean = false, args: Bundle? = null) {
    this.startActivity(Intent(this, T::class.java).apply {
        if (newTask) {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        putExtra("args", args)
    })
}