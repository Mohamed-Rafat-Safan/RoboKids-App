package com.graduationproject.robokidsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.model.Child

class ChildsAdapter(val context: Context,val childList : ArrayList<Child>, val onItemClickListener: OnItemClickListener) : Adapter<ChildsAdapter.ChildViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        return ChildViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_layout_child, parent, false),onItemClickListener)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val child = childList[position]
        holder.bind(child)
    }

    override fun getItemCount(): Int {
        return childList.size
    }

    class ChildViewHolder(itemView: View,onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView){
        val childImage = itemView.findViewById<ImageView>(R.id.img_childs)
        val childName = itemView.findViewById<TextView>(R.id.tv_kids_name)
        init {
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(adapterPosition)
            }
        }
        fun bind(child: Child){
            childName.text = child.childName
            childImage.setImageResource(child.childImage)
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position:Int)
    }

}