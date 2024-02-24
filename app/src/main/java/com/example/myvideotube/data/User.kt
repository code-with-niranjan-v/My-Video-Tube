package com.example.myvideotube.data

data class User(
    val channelName:String = "",
    val userID:String = "",
    val email:String = "",
    val subscribers:MutableList<User>? = mutableListOf(),
    val subscribing:MutableList<User>? = mutableListOf(),
    var videos:MutableList<Video>? = mutableListOf()
)
