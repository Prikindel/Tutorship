package com.prike.tutorship.domain.interactor

import com.prike.tutorship.domain.type.Either
import com.prike.tutorship.domain.type.exception.Failure

abstract class UseCaseFirebase<out Type, in Params> {
    private lateinit var either: Either<Failure, Type>

    abstract fun run(params: Params)

    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
        run(params)
    }
}