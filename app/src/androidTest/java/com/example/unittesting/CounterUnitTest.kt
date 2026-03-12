package com.example.unittesting

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.unittesting.ui.theme.CounterScreen
import org.junit.Rule
import org.junit.Test

class CounterScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun counter_increments_when_button_clicked() {

        composeTestRule.setContent {
            CounterScreen()
        }

        composeTestRule
            .onNodeWithTag("incrementButton")
            .performClick()

        composeTestRule
            .onNodeWithTag("counterText")
            .assertTextEquals("Count: 1")
    }
}