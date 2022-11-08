package com.graduationproject.robokidsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.FragmentForgotPasswordBinding
import com.graduationproject.robokidsapp.databinding.FragmentRegisterBinding

class ForgotPasswordFragment : Fragment() {
    private lateinit var mNavController: NavController
    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)

        binding.btnSendEmail.setOnClickListener {
            val action = ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToEmailSendingSuccessFragment()
            mNavController.navigate(action)
        }

        binding.ivBack.setOnClickListener {
            val action = ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment()
            mNavController.navigate(action)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}