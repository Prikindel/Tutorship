package com.prike.tutorship.domain.type

/**
 * Содержит в себе типы ошибок API
 */

sealed class Failure {
    object NetworkConnectionError : Failure()
    object ServerError : Failure()

    object AuthError : Failure()
    object EmailAlreadyExistError : Failure()
    object UserIsNotFound: Failure()
}