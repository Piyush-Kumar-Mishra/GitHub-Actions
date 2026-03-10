package com.example.unittesting

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


class AuthenticationTest {
    lateinit var auth : Authentication
    @Before
    fun setup(){
        auth = Authentication()
    }

    @Test
    fun `signup test for username size less than 6 return error`() {
        val ans = auth.signup("user", "test@gmail.com" , "12344421")
        assertEquals("username must be greater than 6 char" , ans)
    }

    @Test
    fun `signup test for password size less than 6 return error`(){
        val ans = auth.signup("user12422" , "user@gmail.com" , "pas")
        assertEquals("Password must be greater than 6 char" , ans)

    }

    @Test
    fun `checks correct email format returns error` (){
        val ans = auth.signup("username123" , "usergmail.com" , "password121")
        assertEquals("Enter valid email" , ans)
    }
}