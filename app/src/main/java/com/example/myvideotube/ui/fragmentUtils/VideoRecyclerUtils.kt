package com.example.myvideotube.ui.fragmentUtils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.myvideotube.data.Video
import com.example.myvideotube.databinding.ListOfVideosBinding

class VideoViewHolder(
    private val binding:ListOfVideosBinding,
):ViewHolder(binding.root) {
    fun bindData(videoData:Video,context: Context,listener: VideoListener){
        binding.tvVideoTitle.text = videoData.title
        binding.tvChannelName.text = videoData.channelName
        Glide.with(context)
            .load(videoData.thumbnail)
            .into(binding.imgVideo)

        binding.root.setOnClickListener {
            listener.onCLick(videoData)
        }
    }
}

class VideoAdapter(
    private val listOfVideo:List<Video>,
    private val context: Context,
    private val listener:VideoListener
): Adapter<VideoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ListOfVideosBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return VideoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listOfVideo.size
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bindData(listOfVideo[position],context,listener)

    }


}

interface VideoListener{
    fun onCLick(videoData: Video)
}