package com.graduationproject.robokidsapp.ui.kidsFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.adapters.EducationalContentAdapter
import com.graduationproject.robokidsapp.databinding.FragmentContentBinding
import com.graduationproject.robokidsapp.databinding.FragmentEducationLevelsBinding
import com.graduationproject.robokidsapp.databinding.FragmentEducationalContentBinding
import com.graduationproject.robokidsapp.model.EducationalContent
import com.graduationproject.robokidsapp.model.EducationalLevels
import com.graduationproject.robokidsapp.ui.parentsFragments.AddKidsFragmentDirections

class EducationalContentFragment : Fragment(),EducationalContentAdapter.OnItemClickListener {

    private var _binding: FragmentEducationalContentBinding? = null
    private val binding get() = _binding!!
    private lateinit var mNavController: NavController
    private lateinit var contentList:ArrayList<EducationalLevels>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEducationalContentBinding.inflate(inflater, container, false)

        binding.back.setOnClickListener {
            val action = EducationalContentFragmentDirections.actionEducationalContentFragmentToContentFragment(null)
            mNavController.navigate(action)
        }

        contentList = ArrayList<EducationalLevels>()
        contentList.add(EducationalLevels("contentName1",R.drawable.content))
        contentList.add(EducationalLevels("contentName2",R.drawable.content))
        contentList.add(EducationalLevels("contentName3",R.drawable.content))
        contentList.add(EducationalLevels("contentName4",R.drawable.content))
        contentList.add(EducationalLevels("contentName5",R.drawable.content))
        contentList.add(EducationalLevels("contentName6",R.drawable.content))

        val adapter = EducationalContentAdapter(requireContext(),false,contentList,this)
        binding.rvEducationContent.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.rvEducationContent.adapter = adapter
        binding.rvEducationContent.setHasFixedSize(true)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int) {
        val content = contentList[position]
        val action = EducationalContentFragmentDirections.actionEducationalContentFragmentToEducationLevelsFragment(content)
        mNavController.navigate(action)
    }

}