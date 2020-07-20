package com.prike.tutorship.domain.type.exception

/**
 * Содержит в себе типы ошибок API
 */

sealed class Failure {
    object NetworkConnectionError : Failure()
    object ServerError : Failure()
}