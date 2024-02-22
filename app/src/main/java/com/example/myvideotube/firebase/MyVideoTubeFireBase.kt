package com.example.myvideotube.firebase

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class MyVideoTubeFireBase @Inject constructor(
    private val firebaseAuth:FirebaseAuth
) {

    fun createUserWithPassword(email:String,password:String){
        firebaseAuth.createUserWithEmailAndPassword(email,password)
    }

    fun signIn(email: String,password: String) = firebaseAuth.signInWithEmailAndPassword(email, password)

}