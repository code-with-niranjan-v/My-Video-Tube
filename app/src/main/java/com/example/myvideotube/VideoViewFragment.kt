package com.example.myvideotube

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.fragment.navArgs
import com.example.myvideotube.databinding.FragmentVideoBinding
import com.example.myvideotube.databinding.FragmentVideoViewBinding


class VideoViewFragment : Fragment() {


    private lateinit var videoVideBinding:FragmentVideoViewBinding
    private val videoData:VideoViewFragmentArgs by navArgs()
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
            val player = ExoPlayer.Builder(requireContext()).build()
            videoVideBinding.videoPlayer.player = player
            val mediaItem = MediaItem.fromUri(videoData.videoData!!.videoUrl)
            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()
        }
    }
}