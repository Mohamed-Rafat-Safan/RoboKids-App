package com.graduationproject.robokidsapp.ui.kidsFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.adapters.ContentAdapter
import com.graduationproject.robokidsapp.databinding.FragmentEducationalContentBinding
import com.graduationproject.robokidsapp.model.Content


class EducationalContentFragment : Fragment(),ContentAdapter.OnItemClickListener {

    private var _binding: FragmentEducationalContentBinding? = null
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
        _binding = FragmentEducationalContentBinding.inflate(inflater, container, false)

        binding.educationalContentBack.setOnClickListener {
            mNavController.currentBackStackEntry?.let { backEntry -> mNavController.popBackStack(backEntry.destination.id,true) }
        }

        listContent = ArrayList()
        listContent.add(Content("Pronunciation",R.drawable.main_pronounce))
        listContent.add(Content("Board",R.drawable.main_board))
        listContent.add(Content("Questions",R.drawable.solving_problems))


        val adapter = ContentAdapter(requireContext() , listContent , this)
        binding.rvEducationContent.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.rvEducationContent.adapter = adapter
        binding.rvEducationContent.setHasFixedSize(true)


        return binding.root
    }


    override fun onItemClick(position: Int) {
        val content = listContent[position]
        val action = EducationalContentFragmentDirections.actionEducationalContentFragmentToEducationalSectionFragment(content.contentName)
        mNavController.navigate(action)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}