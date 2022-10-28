package com.graduationproject.robokidsapp.ui.fragments

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.text.TextUtilsCompat
import androidx.core.view.ViewCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.FragmentParentsORKidsBinding
import com.graduationproject.robokidsapp.model.Kids
import com.graduationproject.robokidsapp.ui.MainActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class ParentsORKidsFragment : Fragment() {
    private var _binding: FragmentParentsORKidsBinding? = null
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
        _binding = FragmentParentsORKidsBinding.inflate(inflater, container, false)

        val isLeftToRight = TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == ViewCompat.LAYOUT_DIRECTION_LTR

        if(!isLeftToRight){
            binding.kidsEntry.setImageResource(R.drawable.kids_arabic)
            binding.parentsEntry.setImageResource(R.drawable.parents_arabic)
        }

        binding.kidsEntry.setOnClickListener {

        }

        binding.parentsEntry.setOnClickListener {
            val action = ParentsORKidsFragmentDirections.actionParentsORKidsFragmentToWelcomeFragment()
            mNavController.navigate(action)
        }

        return binding.root
    }








//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        val action = ParentsORKidsFragmentDirections.actionParentsORKidsFragmentToWelcomeFragment()
//        mNavController.navigate(action)
//    }


}