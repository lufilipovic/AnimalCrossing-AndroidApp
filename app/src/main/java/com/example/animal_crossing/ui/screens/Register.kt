package com.example.animal_crossing.ui.screens

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.animal_crossing.data.api.viewModel.AuthViewModel
import com.example.animal_crossing.ui.navigation.NavDrawerItem
import com.google.firebase.auth.FirebaseAuth

@Composable
fun RegisterUI(onSuccessfulLogin: () -> Unit, authViewModel: AuthViewModel = viewModel(), navigationController: NavHostController) {

    val localContext = LocalContext.current

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Register",
            fontFamily = FontFamily.Cursive,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            //color = Color.Magenta,
            fontSize = 40.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Enter your email") },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "person")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, top = 10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)

        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Enter your password") },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "password")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, top = 10.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        RegisterFooter(
            onSignUpClick = {

                when {
                    email.isBlank() -> {
                        // TODO: user error field
                        localContext.showToast("Enter username")
                    }

                    password.isBlank() -> {
                        localContext.showToast("Enter password")
                    }
                    else -> {
                        authViewModel.performRegister(email, password, navigationController)
                        navigationController.navigate(NavDrawerItem.VillagersScreen.route)

                    }
                }

            },
            onSignInClick = {
                navigationController.navigate(NavDrawerItem.LoginScreen.route)
            }
        )

    }
}

@Composable
fun RegisterFooter(
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onSignUpClick, modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Register")
        }
        TextButton(onClick = onSignInClick) {
            Text(text = "Already have an account? Login here")
        }
    }

}

private fun Context.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}


