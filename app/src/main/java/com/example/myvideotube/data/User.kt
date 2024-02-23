package com.example.myvideotube.data

data class User(
    val channelName:String,
    val userID:String,
    val email:String,
    val subscribers:List<User>?,
    val subscribing:List<User>?,
    val videos:List<Video>?
)
