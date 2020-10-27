package com.prike.tutorship

/**
 * Функция, чей результат (ожидаемо) не будет возвращен
 *
 * @param message Сообщение ошибки
 * @return Nothing
 */
fun Any.fail(message: String): Nothing = throw IllegalArgumentException(message)