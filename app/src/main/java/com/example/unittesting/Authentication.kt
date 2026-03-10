package com.example.unittesting

/*
 after adding plugin and dependencies run ./gradlew detekt (it will show error , which tells us it is working)
 ( go to -> under project -> app> buid > reports > detekt.xml)
  it is showing the error because the detekt is not followed from starting
  to resolve the probelem we need baseline which will ignore the codes written previously before implementing detekt ./gradlew detektBaseline
  then go to detekt-baseline.xml
  now run the detekt , it will not show error
*/




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

