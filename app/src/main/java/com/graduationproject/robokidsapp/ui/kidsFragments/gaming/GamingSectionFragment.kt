package com.graduationproject.robokidsapp.ui.kidsFragments.gaming

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.FragmentGamingSectionBinding
import com.graduationproject.robokidsapp.databinding.FragmentParentsDataBinding
import com.graduationproject.robokidsapp.ui.parentsFragments.SplashFragmentDirections


class GamingSectionFragment : Fragment() {
    private var _binding: FragmentGamingSectionBinding? = null
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
        _binding = FragmentGamingSectionBinding.inflate(inflater, container, false)

        binding.XOGame.setOnClickListener {
            val action = GamingSectionFragmentDirections.actionGamingSectionFragmentToTicTacToeFragment()
            mNavController.navigate(action)
        }

        binding.memoryGame.setOnClickListener {
            val action = GamingSectionFragmentDirections.actionGamingSectionFragmentToMemoryGameFragment()
            mNavController.navigate(action)
        }

        binding.gamingSectionBack.setOnClickListener {
            val action = GamingSectionFragmentDirections.actionGamingSectionFragmentToIntertainmentContentFragment()
            mNavController.navigate(action)
        }

        if(MemoryGameFragment.flag){
            MemoryGameFragment.flag = false
            val action = GamingSectionFragmentDirections.actionGamingSectionFragmentToMemoryGameFragment()
            mNavController.navigate(action)
        }

        return binding.root
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}