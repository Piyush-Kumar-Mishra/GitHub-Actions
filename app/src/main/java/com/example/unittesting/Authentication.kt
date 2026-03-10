package com.example.unittesting

class Authentication {
    val regex = "^[A-Za-z0-9+_.-]+@(.+)$".toRegex()
    fun signup(username : String,email: String, password: String) = when {
        username.length < 6 -> {
            "username must be greater than 6 char"
        }
        !regex.matches(email) ->{
            "Enter valid email"
        }
        password.length < 6 -> {
            "Password must be greater than 6 char"
        }
        else -> {
            "signup Successfull"
        }
    }

    fun login(username: String, password: String) = when{
        username.length < 6 -> {
            "username must be greater than 6 char"
        }
        password.length < 6 -> {
            "Password must be greater than 6 char"
        }
        else ->{
            "login successfull"
        }
    }
}

