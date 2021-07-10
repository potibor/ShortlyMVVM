package com.example.shortlyappipeuya.util

import java.io.IOException

sealed class Failure : IOException() {
    object IgnorableError : Failure()
    class UnknownError(val throwable: Throwable) : Failure()
}