package com.example.myvideotube.di

import com.example.myvideotube.firebase.MyVideoTubeFireBase
import com.example.myvideotube.repository.MyVideoTubeRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.google.firestore.v1.FirestoreGrpc.FirestoreImplBase
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
    fun provideFireBaseStoreDb():FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideFireBaseStorage():FirebaseStorage = Firebase.storage

    @Provides
    @Singleton
    fun provideFireBase(firebaseAuth: FirebaseAuth,firestore: FirebaseFirestore,firebaseStorage: FirebaseStorage):MyVideoTubeFireBase = MyVideoTubeFireBase(firebaseAuth,firestore,firebaseStorage)

    @Provides
    @Singleton
    fun provideRepo(fireBase: MyVideoTubeFireBase):MyVideoTubeRepository = MyVideoTubeRepository(fireBase)

}