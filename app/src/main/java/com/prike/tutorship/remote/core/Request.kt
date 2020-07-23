package com.prike.tutorship.remote.core

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.Failure
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Request @Inject constructor(private val networkHandler: NetworkHandler) {
    /*fun <T, R> make(call: Call<T>, transform: (T) -> R): Either<Failure, R> {
        return when (networkHandler.isConnected) {
            true -> execute(call, transform)
            false, null -> Either.Left(Failure.NetworkConnectionError)
        }
    }*/

    fun <T, R> make(task: Task<T>, transform: (T) -> R): Either<Failure, R> {
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

    private fun <T, R> execute(task: Task<T>, transform: (T) -> R): Either<Failure, R> {
        return try {
            val result = Tasks.await(task)
            when (task.isSuccessful) {
                true -> Either.Right(transform(result))
                false -> {
                    Either.Left(task.parseError())
                }

            }
        } catch (exception: Throwable) {
            Either.Left(task.parseError())
        }
    }
}

fun <T> Task<T>.parseError(): Failure {
    Log.i("TAG", "parse ${exception?.message}")
    return when (exception?.message) {
        "The email address is already in use by another account." -> Failure.EmailAlreadyExistError
        else -> Failure.ServerError
    }
}

/*fun <T> Response<T>.isSucceed(): Boolean {
    return isSuccessful && body() != null
}

fun <T> Response<T>.parseError(): Failure {
    return when ("") {
        "" -> Failure.EmailAlreadyExistError
        else -> Failure.ServerError
    }
}*/