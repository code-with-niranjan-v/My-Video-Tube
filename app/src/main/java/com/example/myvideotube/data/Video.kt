package com.example.myvideotube.data

data class Video(
    val title:String,
    val description:String,
    val videoID:String,
    val likes:List<User>?,
    val thumbnail:String,
    val videoUrl:String
)
