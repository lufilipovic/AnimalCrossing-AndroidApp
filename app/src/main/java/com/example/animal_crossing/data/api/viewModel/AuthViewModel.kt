package com.example.animal_crossing.data.api.viewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.animal_crossing.repo.FirebaseAuthentication
import com.example.animal_crossing.ui.navigation.NavDrawerItem
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel() : ViewModel() {
    private val _userLoginStatus = MutableStateFlow<UserLoginStatus?>(null)
    val userLoginStatus = _userLoginStatus.asStateFlow()
    private val firebaseAuth = FirebaseAuth.getInstance()

    private val _userRegisterStatus = MutableStateFlow<UserLoginStatus?>(null)
    val userRegisterStatus = _userRegisterStatus.asStateFlow()

    fun performLogin(email: String, password: String, navigationController: NavHostController) {
        FirebaseAuthentication.login(
            firebaseAuth,
            email,
            password,
            onSuccess = {
                _userLoginStatus.value = UserLoginStatus.Successful
                navigationController.navigate(NavDrawerItem.BugsScreen.route)
            },
            onFailure = {
                _userLoginStatus.value = UserLoginStatus.Failure(it)
            }
        )
    }

    fun performLogout() {
        FirebaseAuthentication.logout(firebaseAuth)
    }

    fun performRegister(email: String, password: String, navigationController: NavHostController) {
        FirebaseAuthentication.register(
            firebaseAuth,
            email,
            password,
            onSuccess = {
                _userRegisterStatus.value = UserRegisterStatus.Successful
                navigationController.navigate(NavDrawerItem.BugsScreen.route)

            },
            onFailure = {
                _userRegisterStatus.value = UserRegisterStatus.Failure(it)
            }
        )
    }
}

sealed class UserLoginStatus {
    object Successful : UserLoginStatus()
    class Failure(val exception: Exception?) : UserLoginStatus()
}

sealed class UserRegisterStatus {
    object Successful : UserLoginStatus()
    class Failure(val exception: Exception?) : UserLoginStatus()
}
