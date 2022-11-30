package com.graduationproject.robokidsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.model.EducationalContent
import com.graduationproject.robokidsapp.model.EducationalLevels

class EducationalContentAdapter(val context: Context,val isLevels:Boolean, val contentList : ArrayList<EducationalLevels>, val onItemClickListener: EducationalContentAdapter.OnItemClickListener):Adapter<EducationalContentAdapter.EducationalContentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationalContentViewHolder {
        if (isLevels){
            return EducationalContentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_layout_levels, parent, false), onItemClickListener)
        }else{
            return EducationalContentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_layout_contents, parent, false), onItemClickListener)
        }
    }

    override fun onBindViewHolder(holder: EducationalContentViewHolder, position: Int) {
        val content = contentList[position]
        holder.bind(content)
    }

    override fun getItemCount(): Int {
        return contentList.size
    }


    inner class EducationalContentViewHolder(itemView: View, onItemClickListener: EducationalContentAdapter.OnItemClickListener) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(adapterPosition)
            }
        }
        fun bind(content: EducationalLevels){
            if(isLevels){
                val levelImage = itemView.findViewById<ImageView>(R.id.img_levels)
                val levelName = itemView.findViewById<TextView>(R.id.tv_level_content_name)
                val bgLock = itemView.findViewById<LinearLayout>(R.id.bg_lock)
                levelName.text = content.levelName
                levelImage.setImageResource(content.levelImage)
                if (content.status){
                    bgLock.visibility = View.GONE
                }else{
                    bgLock.visibility = View.VISIBLE
                }
            }else{
                val contentImage = itemView.findViewById<ImageView>(R.id.img_content)
                val contentName = itemView.findViewById<TextView>(R.id.tv_content_name)
                contentName.text = content.contentName
                contentImage.setImageResource(content.contentImage)
            }

        }
    }

    interface OnItemClickListener{
        fun onItemClick(position:Int)
    }

}