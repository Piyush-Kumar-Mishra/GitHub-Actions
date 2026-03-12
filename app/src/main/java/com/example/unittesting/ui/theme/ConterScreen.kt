package com.example.unittesting.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun CounterScreen() {

    var count by remember { mutableStateOf(0) }

    Column {
        Text(
            text = "Count: $count",
            modifier = Modifier.testTag("counterText")
        )

        Button(
            onClick = { count++ },
            modifier = Modifier.testTag("incrementButton")
        ) {
            Text("Increment")
        }
    }
}