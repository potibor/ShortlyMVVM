package com.example.shortlyappipeuya.util

import java.io.IOException

sealed class Failure : IOException() {
    object IgnorableError : Failure()
}