package com.graduationproject.robokidsapp.ui.kidsFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.adapters.EducationalContentAdapter
import com.graduationproject.robokidsapp.databinding.FragmentContentBinding
import com.graduationproject.robokidsapp.databinding.FragmentEducationLevelsBinding
import com.graduationproject.robokidsapp.databinding.FragmentEducationalContentBinding
import com.graduationproject.robokidsapp.model.EducationalLevels
import com.graduationproject.robokidsapp.ui.parentsFragments.AddKidsFragmentDirections

class EducationLevelsFragment : Fragment() ,EducationalContentAdapter.OnItemClickListener {

    private var _binding: FragmentEducationLevelsBinding? = null
    private val binding get() = _binding!!
    private lateinit var mNavController: NavController
    private lateinit var levelsList:ArrayList<EducationalLevels>
    private val args by navArgs<EducationLevelsFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEducationLevelsBinding.inflate(inflater, container, false)

        binding.back.setOnClickListener {
            val action = EducationLevelsFragmentDirections.actionEducationLevelsFragmentToEducationalContentFragment()
            mNavController.navigate(action)
        }

        binding.tvLevelsContentName.text = args.currentContent?.contentName

        levelsList = ArrayList<EducationalLevels>()
        levelsList.add(EducationalLevels(true,"level 1",R.drawable.content))
        levelsList.add(EducationalLevels(false,"level 2",R.drawable.content))
        levelsList.add(EducationalLevels(false,"level 3",R.drawable.content))
        levelsList.add(EducationalLevels(false,"level 4",R.drawable.content))
        levelsList.add(EducationalLevels(false,"level 5",R.drawable.content))

        val adapter = EducationalContentAdapter(requireContext(),true,levelsList,this)
        binding.rvEducationLevels.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.rvEducationLevels.adapter = adapter
        binding.rvEducationLevels.setHasFixedSize(true)
        

        return binding.root
    }

    override fun onItemClick(position: Int) {
        val level = levelsList[position]
        Toast.makeText(requireContext(), ""+level.status, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}