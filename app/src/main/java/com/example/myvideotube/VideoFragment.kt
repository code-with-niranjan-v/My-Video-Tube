package com.example.myvideotube

import android.content.res.ColorStateList
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myvideotube.data.Video
import com.example.myvideotube.databinding.FragmentVideoBinding
import com.example.myvideotube.ui.fragmentUtils.VideoAdapter
import com.example.myvideotube.ui.fragmentUtils.VideoListener
import com.example.myvideotube.viewmodel.MyVideoTubeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoFragment : Fragment(),VideoListener {

    private lateinit var videoBinding: FragmentVideoBinding
    private val viewModel: MyVideoTubeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        videoBinding = FragmentVideoBinding.inflate(layoutInflater,container,false)
        return videoBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoBinding.circularProgressBar.indeterminateTintList = ColorStateList.valueOf(
            ContextCompat.getColor(requireContext(),R.color.white))

        viewModel.loadAllVideo()



        viewModel.listOfVideos.observe(viewLifecycleOwner){
            if(!it.isNullOrEmpty()){
                videoBinding.circularProgressBar.visibility = View.GONE
                videoBinding.videoLayout.visibility = View.VISIBLE
            }
            Log.e("VideoLoading1",it.toString())
            val adapter = VideoAdapter(it,requireContext(),this)
            videoBinding.videoRV.layoutManager = LinearLayoutManager(requireContext())
            videoBinding.videoRV.adapter = adapter
        }


    }

    override fun onCLick(videoData:Video) {
        val navController = Navigation.findNavController(requireActivity(),R.id.homeContainer)
        val bundle = Bundle()
        bundle.putSerializable("VideoData",videoData)
        navController.navigate(R.id.action_videoFragment2_to_videoViewFragment2,bundle)
    }


}