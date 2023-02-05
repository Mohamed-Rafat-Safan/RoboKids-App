package com.graduationproject.robokidsapp.ui.parentsFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.FragmentAddKidsBinding

class AddKidsFragment : Fragment() {
    private lateinit var mNavController: NavController
    private var _binding: FragmentAddKidsBinding? = null
    private val binding get() = _binding!!

    private lateinit var operation:String

    companion object{
        var conterAge = 1
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
        _binding = FragmentAddKidsBinding.inflate(inflater, container, false)

        operation = arguments?.getString("operation")!!

        checkOperation()

        binding.ivSelectBoy.setOnClickListener {
            binding.ivSelectBoy.setBackgroundResource(R.drawable.bg_select_gender)
            binding.ivSelectGirl.setBackgroundResource(0)
        }

        binding.ivSelectGirl.setOnClickListener {
            binding.ivSelectGirl.setBackgroundResource(R.drawable.bg_select_gender)
            binding.ivSelectBoy.setBackgroundResource(0)
        }


        binding.ivSubKidsAge.setOnClickListener {
            var age:Int = binding.tvAgeKids.text.toString().toInt()
            if(age>1){
                age--
            }
            binding.tvAgeKids.text = age.toString()
        }

        binding.ivPlusKidsAge.setOnClickListener {
            var age:Int = binding.tvAgeKids.text.toString().toInt()
            if(age in 1..6){
                age++
            }
            binding.tvAgeKids.text = age.toString()
        }

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


    private fun checkOperation(){
        if(operation == "editKids"){
            binding.tvAddKidsTitle.text = getString(R.string.edit_kids)
            binding.btnAddKids.text = getString(R.string.update_kids)
        }
    }

}