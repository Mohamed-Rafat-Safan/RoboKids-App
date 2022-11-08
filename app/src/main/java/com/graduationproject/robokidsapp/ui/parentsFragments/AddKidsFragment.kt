package com.graduationproject.robokidsapp.ui.parentsFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.graduationproject.robokidsapp.databinding.FragmentAddKidsBinding

class AddKidsFragment : Fragment() {
    private lateinit var mNavController: NavController
    private var _binding: FragmentAddKidsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddKidsBinding.inflate(inflater, container, false)

        binding.btnAddKids.setOnClickListener {
            val action = AddKidsFragmentDirections.actionAddKidsFragmentToParentsHomeFragment()
            mNavController.navigate(action)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}