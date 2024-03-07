package com.example.myvideotube.repository

import android.net.Uri
import com.example.myvideotube.data.Video
import com.example.myvideotube.firebase.MyVideoTubeFireBase

class MyVideoTubeRepository(
    private val fireBase: MyVideoTubeFireBase
) {

    val listOfVideo:MutableList<Video> = mutableListOf()
    fun uploadVideoAndImage(videoData: Video, selectedVideo: Uri, selectedPhoto: Uri,sendNotification:(Int,Int,String)->Unit){
        fireBase.UploadVideoWithUser(videoData, selectedVideo, selectedPhoto,sendNotification)
    }

    suspend fun loadAllVideo():MutableList<Video>{
        return fireBase.loadAllVideos()
    }

    suspend fun onSearchVideo(query:String):MutableList<Video> = fireBase.onSearchView(query)

}