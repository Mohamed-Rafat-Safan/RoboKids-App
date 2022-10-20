package com.graduationproject.robokidsapp.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.graduationproject.robokidsapp.databinding.FragmentKidsReportsBinding
import com.graduationproject.robokidsapp.ui.MainActivity

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

//        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.customToolbarReports)
//
//
//        // لكي يستدعي الداله الي تحت الي اسمها  onNavigationItemSelected
//        navigationView.setNavigationItemSelectedListener(this)
//
//
//        val actionToggle = ActionBarDrawerToggle(this, drawLayout , toolBar ,
//            R.string.draw_open , R.string.draw_close)
//
//        drawLayout.addDrawerListener(actionToggle)

        return binding.root
    }


}