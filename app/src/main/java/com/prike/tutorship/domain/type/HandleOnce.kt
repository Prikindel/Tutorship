package com.prike.tutorship.domain.type

/**
 * Предотвращает повторное получение данных.
 * Например при повороте экрана не показывать повторно тост
 *
 * @param T
 * @property content
 */

open class HandleOnce<out T>(private val content: T) {
    private var hasBeenHandled = false

    /**
     * Возвращает содержимое и предотвращает его повторное использование.
     *
     * @return T?
     */

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}