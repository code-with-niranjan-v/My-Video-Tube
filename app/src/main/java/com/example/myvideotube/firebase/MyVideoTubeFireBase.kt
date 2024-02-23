package com.example.myvideotube.firebase

import android.util.Log
import com.example.myvideotube.data.User
import com.example.myvideotube.path.FIRESTORE_USER
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject


class MyVideoTubeFireBase @Inject constructor(
    private val firebaseAuth:FirebaseAuth,
    private val fireStore:FirebaseFirestore,
    private val firebaseStorage:FirebaseStorage
) {

    fun createUserWithPassword(email:String,password:String,user: User){
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
            val userData = User(user.channelName,it.user?.uid.toString(),user.email,user.subscribers,user.subscribing,user.videos)
            saveUserData(userData)
        }
    }

    fun signIn(email: String,password: String) = firebaseAuth.signInWithEmailAndPassword(email, password)

    fun saveUserData(user: User){
        fireStore.collection(FIRESTORE_USER)
            .document(user.userID)
            .set(user)
            .addOnCompleteListener{
                if (it.isSuccessful){
                    Log.e("saveUser","Success!")

                }else{
                    Log.e("saveUser","${it.exception?.message}")
                }
            }
    }

}