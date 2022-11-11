package com.graduationproject.robokidsapp.ui.kidsFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.FragmentContentBinding
import com.graduationproject.robokidsapp.databinding.FragmentEducationLevelsBinding
import com.graduationproject.robokidsapp.databinding.FragmentEducationalContentBinding
import com.graduationproject.robokidsapp.ui.parentsFragments.AddKidsFragmentDirections

class EducationalContentFragment : Fragment() {

    private var _binding: FragmentEducationalContentBinding? = null
    private val binding get() = _binding!!
    private lateinit var mNavController: NavController

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

        binding.back.setOnClickListener {
            val action = EducationalContentFragmentDirections.actionEducationalContentFragmentToContentFragment()
            mNavController.navigate(action)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}