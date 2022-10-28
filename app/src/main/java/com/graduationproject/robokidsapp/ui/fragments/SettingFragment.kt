package com.graduationproject.robokidsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.FragmentRegisterBinding
import com.graduationproject.robokidsapp.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
    private lateinit var mNavController: NavController

    private var _binding: FragmentSettingBinding? = null
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
        _binding = FragmentSettingBinding.inflate(inflater, container, false)

        binding.goToUpdateAccount.setOnClickListener {
            val action = SettingFragmentDirections.actionSettingFragmentToUpdateAccountFragment()
            mNavController.navigate(action)
        }

        binding.goToChangePassword.setOnClickListener {
            val action = SettingFragmentDirections.actionSettingFragmentToChangePasswordFragment()
            mNavController.navigate(action)
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}