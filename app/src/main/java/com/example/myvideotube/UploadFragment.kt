package com.example.myvideotube

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.example.myvideotube.data.Video
import com.example.myvideotube.databinding.FragmentUploadBinding
import com.example.myvideotube.viewmodel.MyVideoTubeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID

@AndroidEntryPoint
class UploadFragment : Fragment() {

    private lateinit var uploadBinding: FragmentUploadBinding
    private lateinit var selectedVideo: Uri
    private lateinit var selectedPhoto: Uri
    private val viewModel:MyVideoTubeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        uploadBinding = FragmentUploadBinding.inflate(layoutInflater,container,false)
        return uploadBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pickVideo = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){
            if(it!=null){
                selectedVideo = it
            }else{
                Toast.makeText(requireContext(),"Please Select Video",Toast.LENGTH_SHORT).show()
            }
        }

        val pickPhoto = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){
            if(it!=null){
                selectedPhoto = it
            }else{
                Toast.makeText(requireContext(),"Please Select Thumbnail",Toast.LENGTH_SHORT).show()
            }
        }

        uploadBinding.btnUploadVideo.setOnClickListener {
            pickVideo.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly))
        }
        uploadBinding.btnUploadThumbnail.setOnClickListener {
            pickPhoto.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        uploadBinding.btnUpload.setOnClickListener {
            val title = uploadBinding.etTitle.text.toString()
            val description = uploadBinding.etDescription.text.toString()
            val videoID = UUID.randomUUID().toString()
            val video = Video(title,description,videoID,null,"","")
            if(title.isNotBlank() && description.isNotBlank() && !selectedPhoto.toString().isNullOrBlank() && !selectedVideo.toString().isNullOrBlank()){
                viewModel.uploadVideoAndImage(video,selectedVideo, selectedPhoto)
            }else{
                Toast.makeText(context,"Try Fill All Details or files",Toast.LENGTH_SHORT).show()
            }
        }
    }


}