package com.example.animal_crossing.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.animal_crossing.data.api.viewModel.AuthViewModel
import com.example.animal_crossing.ui.navigation.Screen

@Composable
fun GameDetailView(authViewModel: AuthViewModel = viewModel(), navigationController: NavHostController){

    Button(
        onClick = {
            authViewModel.performLogout()
            navigationController.navigate(Screen.LoginScreen.route)

        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Sign Out")
    }
}