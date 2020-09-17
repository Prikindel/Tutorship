package com.prike.tutorship.remote.core

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

    // function for retrofit
    private fun <T : BaseResponse, R> execute(call: Call<T>, transform: (T) -> R): Either<Failure, R> {
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

fun <T : BaseResponse> Response<T>.isSucceed() = isSuccessful && body() != null && (body() as BaseResponse).success == 1

fun <T : BaseResponse> Response<T>.parseError(): Failure {
    val message = (body() as BaseResponse).message
    return when (message) {
        "email already exists"              -> Failure.EmailAlreadyExistError
        "phone already exists"              -> Failure.PhoneAlreadyExistError
        "error in email or password"        -> Failure.AuthError
        "token is invalid"                  -> Failure.TokenError
        "can't add new token now"           -> Failure.TokenError
        "can't update token now"            -> Failure.TokenError
        "error in country"                  -> Failure.CountryError
        "error in get user"                 -> Failure.UserIsNotFound
        else                                -> Failure.ServerError
    }
}