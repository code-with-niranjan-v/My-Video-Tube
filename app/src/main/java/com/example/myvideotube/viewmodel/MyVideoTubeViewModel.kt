package com.example.myvideotube.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.myvideotube.data.User
import com.example.myvideotube.data.Video
import com.example.myvideotube.firebase.MyVideoTubeFireBase
import com.example.myvideotube.repository.MyVideoTubeRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyVideoTubeViewModel @Inject constructor(
    private val firebase:MyVideoTubeFireBase,
    private val repo:MyVideoTubeRepository
):ViewModel(){

    fun createUserWithPassword(email:String,password:String,user: User){
        CoroutineScope(Dispatchers.IO).launch {
            firebase.createUserWithPassword(email, password,user)
        }
    }

    fun signIn(email: String,password: String) = firebase.signIn(email, password)

    fun uploadVideoAndImage(
        videoData: Video, selectedVideo: Uri, selectedPhoto: Uri,
        sendNotification:(Int,Int,String)-> Unit
    ){
        CoroutineScope(Dispatchers.IO).launch{
            repo.uploadVideoAndImage(videoData, selectedVideo, selectedPhoto,sendNotification)
        }
    }


}