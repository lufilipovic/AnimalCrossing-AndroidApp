package com.example.animal_crossing.ui.screens

import android.content.Context
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
import com.example.animal_crossing.data.api.viewModel.UserLoginStatus
import com.example.animal_crossing.ui.navigation.NavDrawerItem

@Composable
fun LoginUI(onSuccessfulLogin: () -> Unit, authViewModel: AuthViewModel = viewModel(), navigationController: NavHostController) {

    val localContext = LocalContext.current

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    val loginStatus by authViewModel.userLoginStatus.collectAsState()

    var showFailedDialog by remember {
        mutableStateOf(false)
    }


    LaunchedEffect(key1 = loginStatus){
        when (loginStatus) {
            is UserLoginStatus.Failure -> {
                localContext.showToast("Unable to login")
                showFailedDialog = true
            }
            UserLoginStatus.Successful -> {
                localContext.showToast("Login successful")
                onSuccessfulLogin()
            }
            null -> {
                localContext.showToast("Unable to login")
                showFailedDialog = true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Log In",
            fontFamily = FontFamily.Cursive,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            //color = Color.,
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

        LoginFooter(
            onSignInClick = {

                when {
                    email.isBlank() -> {
                        // TODO: user error field
                        localContext.showToast("Enter username")
                    }

                    password.isBlank() -> {
                        localContext.showToast("Enter password")
                    }
                    else -> {
                        authViewModel.performLogin(email, password, navigationController)

                    }
                }

            },
            onSignUpClick = {
                navigationController.navigate(NavDrawerItem.RegisterScreen.route)
            }
        )

//        Button(
//            onClick = { isLight = !isLight }, // Toggle the theme mode when the button is clicked
//            modifier = Modifier.padding(8.dp)
//        ) {
//            Text(if (isLight) "Dark Mode" else "Light Mode")
//        }

    }

    if (showFailedDialog) {
        //toast or text on ui
    }
}

@Composable
fun LoginFooter(
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onSignInClick, modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Sign In")
        }
        TextButton(onClick = onSignUpClick) {
            Text(text = "Don't have an account? Click here")
        }
    }

}
private fun Context.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

