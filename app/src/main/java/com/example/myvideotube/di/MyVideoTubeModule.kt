package com.example.myvideotube.di

import com.example.myvideotube.firebase.MyVideoTubeFireBase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MyVideoTubeModule {

    @Provides
    @Singleton
    fun provideFireBaseAuth():FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun provideFireBase(firebaseAuth: FirebaseAuth):MyVideoTubeFireBase = MyVideoTubeFireBase(firebaseAuth)

}