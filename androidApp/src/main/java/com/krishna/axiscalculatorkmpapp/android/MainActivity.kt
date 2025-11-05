package com.krishna.axiscalculatorkmpapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.krishna.axiscalculatorkmpapp.viewModel.SharedViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType

class MainActivity : ComponentActivity() {

    private val sharedViewModel = SharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                CalculatorScreen(SharedViewModel())
            }
        }
        getData()
    }

    private fun getData(){
        sharedViewModel.loadUsers()
    }

    override fun onDestroy() {
        super.onDestroy()
        sharedViewModel.clear()
    }
}

@Composable
fun CalculatorScreen(viewModel: SharedViewModel) {
    var inputA by remember { mutableStateOf("") }
    var inputB by remember { mutableStateOf("") }

    val result = viewModel.calcResult.collectAsState()
    val user = viewModel.users.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Simple KMP Calculator",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = inputA,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() || it == '.' }) inputA = newValue
                },
                label = { Text("Enter Value A") },
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.DarkGray,
                    cursorColor = Color.Black
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = inputB,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() || it == '.' }) inputB = newValue
                },
                label = { Text("Enter Value B") },
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.DarkGray,
                    cursorColor = Color.Black
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            val buttonShape = RoundedCornerShape(7.dp)
            val buttonColors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1976D2),
                contentColor = Color.White
            )

            Button(
                onClick = { viewModel.calculate("add", inputA, inputB) },
                shape = buttonShape,
                colors = buttonColors
            ) { Text("+") }

            Button(
                onClick = { viewModel.calculate("subtract", inputA, inputB) },
                shape = buttonShape,
                colors = buttonColors
            ) { Text("-") }

            Button(
                onClick = { viewModel.calculate("multiply", inputA, inputB) },
                shape = buttonShape,
                colors = buttonColors
            ) { Text("×") }

            Button(
                onClick = { viewModel.calculate("divide", inputA, inputB) },
                shape = buttonShape,
                colors = buttonColors
            ) { Text("÷") }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Result: ${result.value ?: "N/A"}",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { viewModel.loadUsers() },
            shape = RoundedCornerShape(7.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF232A7A), // blue tone
                contentColor = Color.White
            )
        ) {
            Text("Fetch User Data")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = user.value?.let { "User Title: $it" } ?: "No user data yet",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.DarkGray
        )
    }
}


@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {

    }
}
