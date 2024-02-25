package com.example.myvideotube.data

data class Video(
    val title:String="",
    val description:String="",
    val videoID:String="",
    val likes:MutableList<User>?= mutableListOf(),
    val thumbnail:String="",
    val videoUrl:String="",
    val uid:String="",
    val channelName:String=""
)
