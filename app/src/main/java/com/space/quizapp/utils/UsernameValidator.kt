package com.space.quizapp.utils

object UsernameValidator {
    fun isValidUsername(username: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9][a-zA-Z0-9_]{3,19}$")
        return username.matches(regex)
    }
}