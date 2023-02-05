package com.graduationproject.robokidsapp.ui.kidsFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.adapters.ContentAdapter
import com.graduationproject.robokidsapp.databinding.FragmentEntertainmentContentBinding

import com.graduationproject.robokidsapp.model.Content

class EntertainmentContentFragment : Fragment() , ContentAdapter.OnItemClickListener {
    private var _binding: FragmentEntertainmentContentBinding? = null
    private val binding get() = _binding!!
    private lateinit var mNavController: NavController
    private lateinit var listContent:ArrayList<Content>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEntertainmentContentBinding.inflate(inflater, container, false)


        listContent = ArrayList()
        listContent.add(Content("Stories", R.drawable.stories))
        listContent.add(Content("Music", R.drawable.music))
        listContent.add(Content("Gaming", R.drawable.games_icon))

        val adapter = ContentAdapter(requireContext() , listContent , this)
        binding.rvEntertainmentContent.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.rvEntertainmentContent.adapter = adapter
        binding.rvEntertainmentContent.setHasFixedSize(true)


        binding.entertainmentContentBack.setOnClickListener {
            mNavController.currentBackStackEntry?.let { backEntry -> mNavController.popBackStack(backEntry.destination.id,true) }
        }


        return binding.root
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    // this when click on any card in recyclerView
    override fun onItemClick(position: Int) {
        if(listContent[position].contentName == "Gaming"){
            val action = EntertainmentContentFragmentDirections.actionIntertainmentContentFragmentToGamingSectionFragment()
            mNavController.navigate(action)
        }else{
            val action = EntertainmentContentFragmentDirections.actionIntertainmentContentFragmentToIntertainmentSectionFragment(listContent[position].contentName)
            mNavController.navigate(action)
        }
    }

}