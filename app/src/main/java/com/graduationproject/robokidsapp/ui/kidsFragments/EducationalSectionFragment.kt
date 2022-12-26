package com.graduationproject.robokidsapp.ui.kidsFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.adapters.ContentAdapter
import com.graduationproject.robokidsapp.adapters.EducationalSectionsAdapter
import com.graduationproject.robokidsapp.databinding.FragmentEducationalSectionBinding
import com.graduationproject.robokidsapp.model.Content
import com.graduationproject.robokidsapp.model.EducationalSections
import org.intellij.lang.annotations.RegExp


class EducationalSectionFragment : Fragment(), EducationalSectionsAdapter.OnItemClickListener {
    private var _binding: FragmentEducationalSectionBinding? = null
    private val binding get() = _binding!!

    private lateinit var mNavController: NavController
    private lateinit var listSection:ArrayList<EducationalSections>

    private lateinit var sectionData:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEducationalSectionBinding.inflate(inflater, container, false)

        // get section name from content screen
        sectionData = arguments?.getString("currentSection")!!

        listSection = ArrayList()

        if(sectionData=="Pronunciation"){
            listSection.add(EducationalSections("Arabic",R.drawable.speek_arapic))
            listSection.add(EducationalSections("English",R.drawable.speek_abc))
            listSection.add(EducationalSections("Math",R.drawable.speek_123))
            listSection.add(EducationalSections("Photo",R.drawable.photos_know))
        }else if(sectionData=="Board"){
            listSection.add(EducationalSections("Arabic",R.drawable.board_arapic))
            listSection.add(EducationalSections("English",R.drawable.board_abc))
            listSection.add(EducationalSections("Math",R.drawable.board_123))
            listSection.add(EducationalSections("Photo",R.drawable.photos_know))
        }else if(sectionData=="Questions"){
            listSection.add(EducationalSections("Arabic",R.drawable.ques_arapic))
            listSection.add(EducationalSections("English",R.drawable.ques_abc))
            listSection.add(EducationalSections("Math",R.drawable.ques_123))
        }


        val adapter = EducationalSectionsAdapter(requireContext() , listSection , this)
        binding.rvEducationSection.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.rvEducationSection.adapter = adapter
        binding.rvEducationSection.setHasFixedSize(true)


        binding.educationalSectionContentName.text = sectionData

        binding.educationalContentBack.setOnClickListener {
            mNavController.currentBackStackEntry?.let { backEntry -> mNavController.popBackStack(backEntry.destination.id,true) }
        }

        return binding.root
    }

    var character = ArrayList<Char>()

    override fun onItemClick(position: Int) {
        val section = listSection[position]
        when(sectionData){
            "Pronunciation"->{
                val action = EducationalSectionFragmentDirections.actionEducationalSectionFragmentToPronunciationLettersFragment()
                mNavController.navigate(action)
            }
            "Board"->{
                val action = EducationalSectionFragmentDirections.actionEducationalSectionFragmentToWhiteboardFragment(section.sectionName)
                mNavController.navigate(action)
            }
            "Questions"->{
                val action = EducationalSectionFragmentDirections.actionEducationalSectionFragmentToMainQuizzesFragment2()
                mNavController.navigate(action)
            }
            else -> println("invalid section Data")

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


