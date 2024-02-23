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
import com.example.myvideotube.databinding.FragmentUploadBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UploadFragment : Fragment() {

    private lateinit var uploadBinding: FragmentUploadBinding
    private lateinit var selectedVideo: Uri
    private lateinit var selectedPhoto: Uri
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
            pickVideo.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }


}