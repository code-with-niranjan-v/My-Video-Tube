package com.example.myvideotube

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.myvideotube.databinding.FragmentVideoViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoViewFragment : Fragment() {


    private lateinit var videoVideBinding:FragmentVideoViewBinding
    private val videoData:VideoViewFragmentArgs by navArgs()
    private var isFullscreen = false
    private var player:ExoPlayer? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        videoVideBinding = FragmentVideoViewBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return videoVideBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(videoData.videoData!= null){
            Log.e("VideoViewTest",videoData.videoData.toString())
            player = ExoPlayer.Builder(requireContext()).build()
            if (player!=null){
                videoVideBinding.videoPlayer.player = player
                val mediaItem = MediaItem.fromUri(videoData.videoData!!.videoUrl)
                player!!.setMediaItem(mediaItem)
                player!!.prepare()
                player!!.play()
            }

        }
        videoVideBinding.fullscreenButton.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(),R.id.homeContainer)
            val bundle = Bundle()
            bundle.putSerializable("VideoData",videoData.videoData)
            navController.navigate(R.id.action_videoViewFragment2_to_fullScreenFragment,bundle)
        }
    }



    override fun onPause() {
        super.onPause()

        if (player!=null){
            player!!.pause()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (player != null){
            player!!.release()
            player = null
        }
    }





}