package com.graduationproject.robokidsapp.ui.kidsFragments.quizzes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.graduationproject.robokidsapp.databinding.FragmentMathQuiz1Binding


class MathQuiz1Fragment : Fragment() {
    private var _binding: FragmentMathQuiz1Binding? = null
    private val binding get() = _binding!!
    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
    }

    companion object{
        var textNumber = ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMathQuiz1Binding.inflate(inflater, container, false)


        binding.btn0.setOnClickListener { changeNumber("0") }
        binding.btn1.setOnClickListener { changeNumber("1") }
        binding.btn2.setOnClickListener { changeNumber("2") }
        binding.btn3.setOnClickListener { changeNumber("3") }
        binding.btn4.setOnClickListener { changeNumber("4") }
        binding.btn5.setOnClickListener { changeNumber("5") }
        binding.btn6.setOnClickListener { changeNumber("6") }
        binding.btn7.setOnClickListener { changeNumber("7") }
        binding.btn8.setOnClickListener { changeNumber("8") }
        binding.btn9.setOnClickListener { changeNumber("9") }

        binding.delete.setOnClickListener {
            if (textNumber.isNotEmpty()){
                textNumber = textNumber.substring(0, textNumber.length - 1)
                binding.tvMathQuiz1Result.text = textNumber
            }
        }

        binding.mathQuiz1CheckAnswer.setOnClickListener {
            Toast.makeText(requireContext(), "ما انت حلو لزوز اه", Toast.LENGTH_SHORT).show()
        }


        binding.mathQuiz1Exit.setOnClickListener {
            mNavController.currentBackStackEntry?.let { backEntry -> mNavController.popBackStack(backEntry.destination.id,true) }
        }

        return binding.root
    }

    private fun changeNumber(num: String) {
        if (textNumber.length < 3) {
            textNumber += num
            binding.tvMathQuiz1Result.text = textNumber
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}