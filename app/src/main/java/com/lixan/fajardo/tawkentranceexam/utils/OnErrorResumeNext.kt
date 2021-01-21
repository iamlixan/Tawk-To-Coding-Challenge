package com.lixan.fajardo.tawkentranceexam.utils

import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer

class OnErrorResumeNext<S: Any, T: Throwable>(
    private val errorObject:S,
    private val throwableToCatchClass: Class<T>
): SingleTransformer<S, S> {


    override fun apply(upstream: Single<S>): SingleSource<S> {
        return upstream
            .onErrorResumeNext {
                if (shouldCatchException(it)) {
                    return@onErrorResumeNext Single.just(errorObject)
                }
                return@onErrorResumeNext Single.error(it)
            }
    }

    private fun shouldCatchException(throwableThrown: Throwable): Boolean {
        return throwableToCatchClass == throwableThrown.javaClass
    }

}