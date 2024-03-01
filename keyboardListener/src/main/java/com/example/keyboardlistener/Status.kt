package com.example.keyboardlistener

sealed class Status(val height: Int? = null) {
    class Open(height: Int) : Status(height)
    class Closed() : Status()
}