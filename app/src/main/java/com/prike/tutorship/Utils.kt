package com.prike.tutorship

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * Функция, чей результат (ожидаемо) не будет возвращен
 *
 * @param message Сообщение ошибки
 * @return Nothing
 */
fun Any.fail(message: String): Nothing = throw IllegalArgumentException(message)

/**
 * Переводит секунды в LocalDateTime
 *
 * @param seconds - Секунды
 * @return LocalDateTime
 */
fun Any.secondsToDate(seconds: Long) = Instant.fromEpochSeconds(seconds).toLocalDateTime(
    TimeZone.currentSystemDefault())