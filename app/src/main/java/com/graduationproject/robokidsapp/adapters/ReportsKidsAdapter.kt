package com.graduationproject.robokidsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.model.Child
import com.graduationproject.robokidsapp.ui.kidsFragments.HomeKidsFragment
import com.graduationproject.robokidsapp.ui.kidsFragments.HomeKidsFragmentDirections
import com.graduationproject.robokidsapp.ui.parentsFragments.ParentsHomeFragment
import com.graduationproject.robokidsapp.ui.parentsFragments.ParentsHomeFragmentDirections


class ReportsKidsAdapter(
    val context: Context,
    val listChild: ArrayList<Child>,
    val onItemClickListener: OnItemClickListener
) : Adapter<ReportsKidsAdapter.ReportsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportsViewHolder {
        return ReportsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_layout_reports_kids, parent, false), onItemClickListener)
    }

    override fun onBindViewHolder(holder: ReportsViewHolder, position: Int) {
        val child = listChild[position]

        holder.bind(child)

        holder.optionChild.setOnClickListener {
            showOptionKids(it)
        }

        holder.btn_reports.setOnClickListener {
            val action = ParentsHomeFragmentDirections.actionParentsHomeFragmentToKidsReportsFragment()
            ParentsHomeFragment.mNavController.navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return listChild.size
    }


    private fun showOptionKids(v:View){
        val popup = PopupMenu(context, v)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.option_kids_menu , popup.menu)

        popup.setOnMenuItemClickListener {item ->
            when(item.itemId){
                R.id.editKids ->{
                    val action = ParentsHomeFragmentDirections.actionParentsHomeFragmentToAddKidsFragment("editKids")
                    ParentsHomeFragment.mNavController.navigate(action)
                }
                R.id.deleteKids ->{

                }
            }
            true
        }

        popup.show()
    }


    class ReportsViewHolder(itemView: View, onItemClickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(adapterPosition)
            }
        }

        val nameChild = itemView.findViewById<TextView>(R.id.tv_reports_kidsName)!!
        val imageChild = itemView.findViewById<ImageView>(R.id.iv_reports_kidsImage)!!
        val optionChild = itemView.findViewById<ImageView>(R.id.iv_kids_options)
        val btn_reports = itemView.findViewById<TextView>(R.id.tv_reports_kids)

        fun bind(child: Child) {
            nameChild.text = child.childName
            imageChild.setImageResource(child.childImage)
        }
    }


    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}