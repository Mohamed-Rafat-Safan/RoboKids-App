package com.graduationproject.robokidsapp.ui.parentsFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.graduationproject.robokidsapp.databinding.FragmentRegisterBinding
import com.hbb20.countrypicker.models.CPCountry


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!


    private lateinit var mNavController: NavController

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

       binding.btnRegister.setOnClickListener {
           val action = RegisterFragmentDirections.actionRegisterFragmentToParentsDataFragment()
           mNavController.navigate(action)
       }


        binding.tvBackToLogin.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            mNavController.navigate(action)
        }

        binding.countryPicker.cpViewHelper.cpViewConfig.viewTextGenerator =  { cpCountry: CPCountry ->

            Toast.makeText(activity, "${cpCountry.name} (${cpCountry.alpha2})" , Toast.LENGTH_SHORT).show()

            "${cpCountry.name} (${cpCountry.alpha2})"
        }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}