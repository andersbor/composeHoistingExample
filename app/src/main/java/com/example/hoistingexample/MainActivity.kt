package com.example.hoistingexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hoistingexample.ui.theme.HoistingExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HoistingExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
private fun MainContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        CounterInternalState()

        var count by rememberSaveable { mutableIntStateOf(0) }
        CounterHoistedState(
            count = count,
            onCountChange = { count = it },
        )
        Text(text = "Count: $count")

    }
}

@Composable
fun CounterInternalState(modifier: Modifier = Modifier) {
    var count: Int by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .padding(16.dp)
            .border(
                border = BorderStroke(1.dp, Color.Gray),
                shape = RoundedCornerShape(4.dp)
            ).padding(8.dp)
    ) {
        Text("You've clicked $count times")
        Button(onClick = { count++ }) {
            Text("Click me")
        }
    }
}

@Composable
fun CounterHoistedState(
    count: Int, onCountChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text("You've clicked $count times")
        Button(onClick = { onCountChange(count + 1) }) {
            Text("Click me")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HoistingExampleTheme {
        CounterInternalState()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    HoistingExampleTheme {
        CounterHoistedState(count = 0, onCountChange = {})
    }
}