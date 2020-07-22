package com.prike.tutorship.remote.core

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.exception.Failure
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton
/*
@Singleton
class Request @Inject constructor(private val networkHandler: NetworkHandler) {
    /*fun <T, R> make(call: Call<T>, transform: (T) -> R): Either<Failure, R> {
        return when (networkHandler.isConnected) {
            true -> execute(call, transform)
            false, null -> Either.Left(Failure.NetworkConnectionError)
        }
    }*/

    fun <T : Task<AuthResult>, R> make(task: Task<AuthResult>, transform: (T) -> R): Either<Failure, R> {
        return when (networkHandler.isConnected){
            true -> execute(task, transform)
            false, null -> Either.Left(Failure.NetworkConnectionError)
        }
    }

    /*private fun <T, R> execute(call: Call<T>, transform: (T) -> R): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSucceed()) {
                true -> Either.Right(transform(response.body()!!))
                false -> Either.Left(response.parseError())
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }*/

    private fun <T : Task<AuthResult>, R> execute(task: Task<AuthResult>, transform: (T) -> R): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSucceed()) {
                true -> Either.Right(transform(response.body()!!))
                false -> Either.Left(response.parseError())
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }
}

fun <T> Response<T>.isSucceed(): Boolean {
    return isSuccessful && body() != null
}

fun <T> Response<T>.parseError(): Failure {
    return when ("") {
        "" -> Failure.EmailAlreadyExistError
        else -> Failure.ServerError
    }
}*/