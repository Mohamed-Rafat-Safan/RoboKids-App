package com.graduationproject.robokidsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.FragmentParentsDataBinding


class ParentsDataFragment : Fragment() {
    private var _binding: FragmentParentsDataBinding? = null
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
        _binding = FragmentParentsDataBinding.inflate(inflater, container, false)

        binding.btnParentData.setOnClickListener {
            val action = ParentsDataFragmentDirections.actionParentsDataFragmentToAddKidsFragment()
            mNavController.navigate(action)
        }

        binding.selectYearBirth.setOnClickListener {
            showDialogCountries()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showDialogCountries() {
        val customView = LayoutInflater.from(activity)
            .inflate(R.layout.custom_layout_select_year_birth, null, false)

        val dialog = AlertDialog.Builder(requireContext())
        dialog.setView(customView)

        val alert = dialog.create()
        alert.show()

        val close = customView.findViewById<ImageView>(R.id.iv_close)
        val rvYearBirth = customView.findViewById<RecyclerView>(R.id.rv_yearBirth)


        close.setOnClickListener { alert.dismiss() } // this to close dialog

    }

}