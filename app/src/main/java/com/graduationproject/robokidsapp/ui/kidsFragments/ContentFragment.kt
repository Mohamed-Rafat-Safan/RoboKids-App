package com.graduationproject.robokidsapp.ui.kidsFragments

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.FragmentAddKidsBinding
import com.graduationproject.robokidsapp.databinding.FragmentContentBinding
import com.graduationproject.robokidsapp.databinding.FragmentParentsORKidsBinding
import com.graduationproject.robokidsapp.ui.parentsFragments.AddKidsFragmentDirections

class ContentFragment : Fragment() {

    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!
    private lateinit var mNavController: NavController

    override fun onResume() {
        super.onResume()
        //Hide status bar
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentContentBinding.inflate(inflater, container, false)

        binding.educationalContent.setOnClickListener {
            val action = ContentFragmentDirections.actionContentFragmentToEducationalContentFragment()
            mNavController.navigate(action)

        }

        binding.entertainmentContent.setOnClickListener {
            Toast.makeText(activity, "entertainment", Toast.LENGTH_SHORT).show()

        }

        binding.back.setOnClickListener {
//            val action = ContentFragmentDirections.actionContentFragmentToHomeKidsFragment()
//            mNavController.navigate(action)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}