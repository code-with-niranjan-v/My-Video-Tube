package com.example.myvideotube.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myvideotube.firebase.MyVideoTubeFireBase
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyVideoTubeViewModel @Inject constructor(
    private val firebase:MyVideoTubeFireBase
):ViewModel(){

    fun createUserWithPassword(email:String,password:String){
        CoroutineScope(Dispatchers.IO).launch {
            firebase.createUserWithPassword(email, password)
        }
    }

}