package com.example.myvideotube.firebase

import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.example.myvideotube.data.User
import com.example.myvideotube.data.Video
import com.example.myvideotube.path.FIRESTORAGE_PHOTO
import com.example.myvideotube.path.FIRESTORAGE_VIDEO
import com.example.myvideotube.path.FIRESTORE_USER
import com.example.myvideotube.path.FIRESTORE_VIDEOS
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject


class MyVideoTubeFireBase @Inject constructor(
    private val firebaseAuth:FirebaseAuth,
    private val fireStore:FirebaseFirestore,
    private val firebaseStorage:FirebaseStorage
) {

    fun createUserWithPassword(email:String,password:String,user: User){
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
            val userData = User(user.channelName,it.user?.uid.toString(),user.email,user.subscribers,user.subscribing,user.videos)
            saveUserData(userData)
        }
    }

    fun signIn(email: String,password: String) = firebaseAuth.signInWithEmailAndPassword(email, password)

    fun saveUserData(user: User){
        fireStore.collection(FIRESTORE_USER)
            .document(user.userID)
            .set(user)
            .addOnCompleteListener{
                if (it.isSuccessful){
                    Log.e("saveUser","Success!")

                }else{
                    Log.e("saveUser","${it.exception?.message}")
                }
            }
    }

    fun uploadVideoAndImage(videoData: Video, selectedVideo:Uri,selectedPhoto: Uri,sendNotification:(Int,Int,String)->Unit){
        firebaseStorage.reference.child(FIRESTORAGE_VIDEO).child(videoData.videoID).putFile(selectedVideo).addOnSuccessListener {videotask->
            firebaseStorage.reference.child(FIRESTORAGE_VIDEO).child(videoData.videoID).downloadUrl.addOnSuccessListener {videoUrl->
                firebaseStorage.reference.child(FIRESTORAGE_PHOTO).child(videoData.videoID).putFile(selectedPhoto).addOnCompleteListener{
                    if (it.isSuccessful){
                        val photoUrl = firebaseStorage.reference.child(FIRESTORAGE_PHOTO).child(videoData.videoID).downloadUrl.addOnSuccessListener{photoUrl->
                            val video = Video(videoData.title,videoData.description,videoData.videoID,null,photoUrl.toString(),videoUrl.toString())
                            saveVideo(video)
                            sendNotification(0,0,"Upload Completed")
                        }

                    }
                }.addOnFailureListener{
                    Log.d("uploadVideo","${it.message}")
                    sendNotification(0,0,it.message.toString())
                }
            }

        }.addOnProgressListener {
            val progress = ((100*it.bytesTransferred)/it.totalByteCount).toInt()
            sendNotification(progress,100,"Video Uploading..")
        }
    }

    fun saveVideo(video: Video){
        fireStore.collection(FIRESTORE_VIDEOS).document(video.videoID).set(video)
    }

}