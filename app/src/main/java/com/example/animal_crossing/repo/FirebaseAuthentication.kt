package com.example.animal_crossing.repo

import com.google.firebase.auth.FirebaseAuth
import com.skydoves.landscapist.glide.GlideImageState
import kotlinx.coroutines.flow.MutableStateFlow

object FirebaseAuthentication {

    fun login(
        firebaseAuth: FirebaseAuth,
        username: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception?) -> Unit
    ){

        val userAuthStatus = MutableStateFlow<Boolean?>(null)

        firebaseAuth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    onSuccess()
                    userAuthStatus.value = true
                }else{
                    onFailure(it.exception)
                    userAuthStatus.value = false
                }
            }
    }


    fun register(
        firebaseAuth: FirebaseAuth,
        username: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception?) -> Unit
    ){
        firebaseAuth.createUserWithEmailAndPassword(username, password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    onSuccess()
                }else{
                    onFailure(it.exception)
                }
            }
    }

    fun logout(firebaseAuth: FirebaseAuth){
       firebaseAuth.signOut()
    }
}