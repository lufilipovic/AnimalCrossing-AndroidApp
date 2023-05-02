package com.example.animal_crossing.data.api.viewModel

import androidx.lifecycle.ViewModel
import com.example.animal_crossing.repo.FirebaseAuthentication
import com.example.animal_crossing.ui.FossilScreen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel : ViewModel() {

    private val _userLoginStatus = MutableStateFlow<UserLoginStatus?>(null)
    val userLoginStatus = _userLoginStatus.asStateFlow()
    private val firebaseAuth = FirebaseAuth.getInstance()

    init {
        performRegister("lucija@email.com", "test123456")
    }

    fun performLogin(username: String, password: String) {
        FirebaseAuthentication.login(
            firebaseAuth,
            username,
            password,
            onSuccess = {
                _userLoginStatus.value = UserLoginStatus.Successful
            },
            onFailure = {
                _userLoginStatus.value = UserLoginStatus.Failure(it)
            }
        )
    }

    fun performRegister(username: String, password: String) {
        FirebaseAuthentication.register(
            firebaseAuth,
            username,
            password,
            onSuccess = {

            },
            onFailure = {

            }
        )
    }
}

sealed class UserLoginStatus {
    object Successful : UserLoginStatus()
    class Failure(val exception: Exception?) : UserLoginStatus()
}