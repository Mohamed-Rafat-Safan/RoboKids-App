package com.graduationproject.robokidsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.model.Videos
import com.jackandphantom.carouselrecyclerview.view.ReflectionImageView

class VideoAdapter(val context: Context, val listVideos:ArrayList<Videos>, val onItemClickListener: OnItemClickListener):Adapter<VideoAdapter.VideoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_layout_videos,parent,false) , onItemClickListener)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = listVideos[position]
        holder.bind(video)
    }

    override fun getItemCount(): Int {
        return listVideos.size
    }



    class VideoViewHolder(itemView: View, onItemClickListener: OnItemClickListener): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(adapterPosition)
            }
        }

        val videoImage = itemView.findViewById<ReflectionImageView>(R.id.img_video)!!

        fun bind(videos: Videos){
            videoImage.setImageResource(videos.videoImage)
        }

    }

    interface OnItemClickListener{
        fun onItemClick(position:Int)
    }
}