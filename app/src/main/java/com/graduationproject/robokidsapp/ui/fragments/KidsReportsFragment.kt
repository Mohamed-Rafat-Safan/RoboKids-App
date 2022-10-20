package com.graduationproject.robokidsapp.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.graduationproject.robokidsapp.databinding.FragmentKidsReportsBinding

class KidsReportsFragment : Fragment() {
    private var _binding: FragmentKidsReportsBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentKidsReportsBinding.inflate(inflater, container, false)

        return binding.root
    }


}