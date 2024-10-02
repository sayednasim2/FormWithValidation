package com.sayednasim.formwtvalidation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sayednasim.formwtvalidation.ui.theme.FormWtValidationTheme


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import java.util.regex.Pattern

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FormWtValidationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FormWithValidation(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}



@Composable
fun FormWithValidation(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    val nameRegex = "^[a-zA-Z ]+$"
    val emailRegex = "^[A-Za-z0-9_.-]+@[A-Za-z.]+$"
    val passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Registration Form",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = if (Pattern.matches(nameRegex, it)) "" else "Invalid name"
            },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(),
            isError = nameError.isNotEmpty()
        )
        if (nameError.isNotEmpty()) {
            Text(text = nameError, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = if (Pattern.matches(emailRegex, it)) "" else "Invalid email address"
            },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = emailError.isNotEmpty()
        )
        if (emailError.isNotEmpty()) {
            Text(text = emailError, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = if (Pattern.matches(passwordRegex, it)) "" else "Password must be 8+ chars, 1 letter, 1 number"
            },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            isError = passwordError.isNotEmpty()
        )
        if (passwordError.isNotEmpty()) {
            Text(text = passwordError, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (nameError.isEmpty() && emailError.isEmpty() && passwordError.isEmpty()) {
                    // Form is valid
                    println("Form Submitted")
                } else {
                    // Form is invalid
                    println("Please fix the errors")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = nameError.isEmpty() && emailError.isEmpty() && passwordError.isEmpty()
        ) {
            Text("Submit")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFormWithValidation() {
    FormWithValidation()
}

