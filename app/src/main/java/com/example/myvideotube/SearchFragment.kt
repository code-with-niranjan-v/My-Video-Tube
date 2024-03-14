package com.example.myvideotube

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myvideotube.data.Video
import com.example.myvideotube.databinding.FragmentSearchBinding
import com.example.myvideotube.ui.fragmentUtils.VideoAdapter
import com.example.myvideotube.ui.fragmentUtils.VideoListener
import com.example.myvideotube.viewmodel.MyVideoTubeViewModel
import dagger.hilt.android.AndroidEntryPoint




@AndroidEntryPoint
class SearchFragment : Fragment(),VideoListener {


    private lateinit var searchBinding: FragmentSearchBinding
    private val viewModel:MyVideoTubeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchBinding = FragmentSearchBinding.inflate(layoutInflater,container,false)

        return searchBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = VideoAdapter(mutableListOf(),requireContext(),this)
        searchBinding.rvVideos.layoutManager = LinearLayoutManager(requireContext())
        searchBinding.rvVideos.adapter = adapter
        searchBinding.searchVideo.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    if (query.isNotEmpty()){
                        Log.e("Search1",query.toString())
                        viewModel.onSearchVideo(query.toString())
                        Log.e("Firestore",viewModel.listOfSearchVideo.value.toString())
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        viewModel.listOfSearchVideo.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    override fun onCLick(videoData: Video) {
        val navController = Navigation.findNavController(requireActivity(),R.id.homeContainer)
        val bundle = Bundle()
        bundle.putSerializable("VideoData",videoData)
        navController.navigate(R.id.action_searchFragment2_to_videoViewFragment2,bundle)
    }


}