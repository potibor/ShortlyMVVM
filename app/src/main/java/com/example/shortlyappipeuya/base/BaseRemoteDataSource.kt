package com.example.shortlyappipeuya.base

import com.example.shortlyappipeuya.util.Failure

open class BaseRemoteDataSource {

    /**
     * Invokes the service function, and returns the Output based on given type.
     * throwed failures are being catched on the {@link UseCase}
     *
     * @param serviceFunction the function which returns the desired output
     * @throws Failure
     * */
    suspend fun <O> invoke(serviceFunction: suspend () -> O): O {
        return try {
            serviceFunction()
        } catch (exception: Exception) {
            throw asFailure(exception)
        }
    }

    private fun asFailure(exception: Exception): Failure {
        return when (exception) {
            is Failure -> exception
            else -> Failure.UnknownError(exception)
        }
    }
}