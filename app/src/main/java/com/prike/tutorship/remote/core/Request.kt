package com.prike.tutorship.remote.core

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.Failure
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Request @Inject constructor(private val networkHandler: NetworkHandler) {

    // function for retrofit
    fun <T : BaseResponse, R> make(call: Call<T>, transform: (T) -> R): Either<Failure, R> {
        return when (networkHandler.isConnected) {
            true -> execute(call, transform)
            false, null -> Either.Left(Failure.NetworkConnectionError)
        }
    }

    // function for firebase
    fun <T, R> make(task: Task<T>, transform: (T) -> R): Either<Failure, R> {
        return when (networkHandler.isConnected){
            true -> execute(task, transform)
            false, null -> Either.Left(Failure.NetworkConnectionError)
        }
    }

    // function for retrofit
    private fun <T : BaseResponse, R> execute(call: Call<T>, transform: (T) -> R): Either<Failure, R> {
        return try {
            val response = call.execute()
            Log.i("TAG", response.body().toString())
            when (response.isSucceed()) {
                true -> Either.Right(transform(response.body()!!))
                false -> Either.Left(response.parseError())
            }
        } catch (exception: Throwable) {
            Log.e("TAG", exception.message)
            Either.Left(Failure.ServerError)
        }
    }

    // function for firebase
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
    return when (exception?.message) {
        "The email address is already in use by another account." -> Failure.EmailAlreadyExistError
        "The password is invalid or the user does not have a password." -> Failure.AuthError
        "There is no user record corresponding to this identifier. The user may have been deleted." -> Failure.UserIsNotFound
        else -> Failure.ServerError
    }
}

fun <T : BaseResponse> Response<T>.isSucceed() = isSuccessful && body() != null && (body() as BaseResponse).success == 1

fun <T : BaseResponse> Response<T>.parseError(): Failure {
    val message = (body() as BaseResponse).message
    return when (message) {
        "email already exists"              -> Failure.EmailAlreadyExistError
        "phone already exists"              -> Failure.PhoneAlreadyExistError
        "error in email or password"        -> Failure.AuthError
        "error in country"                  -> Failure.CountryError
        else                                -> Failure.ServerError
    }
}