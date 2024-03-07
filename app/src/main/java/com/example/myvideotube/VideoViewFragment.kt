package com.example.myvideotube

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myvideotube.data.Video
import com.example.myvideotube.databinding.FragmentVideoViewBinding
import com.example.myvideotube.ui.fragmentUtils.VideoAdapter
import com.example.myvideotube.ui.fragmentUtils.VideoListener
import com.example.myvideotube.viewmodel.MyVideoTubeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoViewFragment : Fragment(),VideoListener {


    private lateinit var videoViewBinding:FragmentVideoViewBinding
    private val videoData:VideoViewFragmentArgs by navArgs()
    private lateinit var videoDataNew:Video
    private var isExpanded:Boolean = false
    private val viewModel:MyVideoTubeViewModel by viewModels()
    private var player:ExoPlayer? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        videoViewBinding = FragmentVideoViewBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return videoViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       videoViewBinding.circularProgressBar.indeterminateTintList = ColorStateList.valueOf(
            ContextCompat.getColor(requireContext(),R.color.white))

        videoViewBinding.imgExpand.setOnClickListener {
            isExpanded = !isExpanded
            expandDescription()
        }

        viewModel.loadAllVideo()
        viewModel.listOfVideos.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                videoViewBinding.circularProgressBar.visibility = View.GONE
                videoViewBinding.rvVideo.visibility = View.VISIBLE
                val adapter = VideoAdapter(it, requireContext(), this)
                videoViewBinding.rvVideo.layoutManager = LinearLayoutManager(requireContext())
                videoViewBinding.rvVideo.adapter = adapter
            } else {
                viewModel.loadAllVideo()
            }
        }
            if (videoData.videoData != null) {
//                videoViewBinding.tvDescriptionContent.text = videoData.videoData!!.description
                videoViewBinding.tvVideoTitle.text = videoData.videoData!!.title
                videoViewBinding.tvChannelName.text = videoData.videoData!!.channelName
                Log.e("VideoViewTest", videoData.videoData.toString())
                player = ExoPlayer.Builder(requireContext()).build()
                if (player != null) {
                    videoViewBinding.videoPlayer.player = player
                    val mediaItem = MediaItem.fromUri(videoData.videoData!!.videoUrl)
                    player!!.setMediaItem(mediaItem)
                    player!!.prepare()
                    player!!.play()
                }

            }
            videoViewBinding.fullscreenButton.setOnClickListener {
                val navController =
                    Navigation.findNavController(requireActivity(), R.id.homeContainer)
                val bundle = Bundle()
                bundle.putSerializable("VideoData", videoData.videoData)
                navController.navigate(R.id.action_videoViewFragment2_to_fullScreenFragment, bundle)
            }


    }



        override fun onPause() {
            super.onPause()

            if (player != null) {
                player!!.pause()
            }
        }

        override fun onDestroyView() {
            super.onDestroyView()
            if (player != null) {
                player!!.release()
                player = null
            }
        }

        override fun onCLick(videoData: Video) {
            val navController = Navigation.findNavController(requireActivity(), R.id.homeContainer)
            val bundle = Bundle()
            bundle.putSerializable("VideoData", videoData)
            navController.navigate(R.id.action_videoViewFragment2_self,bundle)
        }

    private fun expandDescription(){
        val visibility = if (isExpanded) View.VISIBLE else View.GONE
        val img = if (isExpanded)ContextCompat.getDrawable(requireContext(),R.drawable.baseline_expand_less_24)  else ContextCompat.getDrawable(requireContext(),R.drawable.baseline_expand_more_24)
        videoViewBinding.tvDescriptionContent.visibility = visibility
        videoViewBinding.imgExpand.setImageDrawable(img)
    }




}