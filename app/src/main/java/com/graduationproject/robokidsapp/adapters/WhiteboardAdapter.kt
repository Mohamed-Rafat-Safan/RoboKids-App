package com.graduationproject.robokidsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.ui.kidsFragments.WhiteboardFragment

class WhiteboardAdapter(val context: Context , val listData:ArrayList<String>):Adapter<WhiteboardAdapter.WhiteboardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhiteboardViewHolder {
        return WhiteboardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_layout_letters , parent,false))
    }

    override fun onBindViewHolder(holder: WhiteboardViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)

        holder.itemView.setOnClickListener {
            WhiteboardFragment.binding.tvText.text = data
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }


    class WhiteboardViewHolder(itemView: View) : ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.tv_letter)
        fun bind(data:String){
            textView.text = data
        }
    }
}