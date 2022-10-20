package com.graduationproject.robokidsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.FragmentRegisterBinding


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

        binding.selectCountry.setOnClickListener {
            showDialogCountries()
        }

        binding.tvBackToLogin.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            mNavController.navigate(action)
        }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun showDialogCountries() {
        val customView = LayoutInflater.from(activity)
            .inflate(R.layout.custom_layout_select_country, null, false)

        val dialog = AlertDialog.Builder(requireContext())
        dialog.setView(customView)

        val alert = dialog.create()
        alert.show()

        val close = customView.findViewById<ImageView>(R.id.iv_close)
        val editTextSearch = customView.findViewById<EditText>(R.id.et_searchCountry)
        val rvCountry = customView.findViewById<RecyclerView>(R.id.rv_countries)


        close.setOnClickListener { alert.dismiss() } // this to close dialog

    }

}