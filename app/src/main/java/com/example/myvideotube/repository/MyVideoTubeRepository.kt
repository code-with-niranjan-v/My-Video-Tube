package com.example.myvideotube.repository

import android.net.Uri
import com.example.myvideotube.data.Video
import com.example.myvideotube.firebase.MyVideoTubeFireBase

class MyVideoTubeRepository(
    private val fireBase: MyVideoTubeFireBase
) {

    fun uploadVideoAndImage(videoData: Video, selectedVideo: Uri, selectedPhoto: Uri,sendNotification:(Int,Int,String)->Unit){
        fireBase.uploadVideoAndImage(videoData, selectedVideo, selectedPhoto,sendNotification)
    }

}