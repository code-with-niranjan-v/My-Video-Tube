package com.example.myvideotube

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.myvideotube.databinding.FragmentFullScreenBinding
import com.example.myvideotube.databinding.FragmentHomeBinding
import com.example.myvideotube.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class FullScreenFragment : Fragment() {

    private lateinit var fullScreenBinding: FragmentFullScreenBinding
    private val videoData:FullScreenFragmentArgs by navArgs()
    private var player: ExoPlayer? = null
    private val sharedViewModel:SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fullScreenBinding = FragmentFullScreenBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return fullScreenBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeBinding = FragmentHomeBinding.inflate(layoutInflater)

        if(videoData.videoData!= null){
            sharedViewModel.hideBottomNavigationBar.value = true
            Log.e("VideoViewTest",videoData.videoData.toString())
            player = ExoPlayer.Builder(requireContext()).build()
            if (player!=null){
                fullScreenBinding.fullScreenPlayer.player = player
                val mediaItem = MediaItem.fromUri(videoData.videoData!!.videoUrl)
                player!!.setMediaItem(mediaItem)
                player!!.prepare()
                player!!.play()
                requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;



            }

        }

        fullScreenBinding.fullscreenExitButton.setOnClickListener {
            sharedViewModel.hideBottomNavigationBar.value = false
            requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
            val navController = Navigation.findNavController(requireActivity(),R.id.homeContainer)
            navController.popBackStack()


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