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
import com.graduationproject.robokidsapp.adapters.VideoAdapter
import com.graduationproject.robokidsapp.databinding.FragmentEntertainmentSectionBinding
import com.graduationproject.robokidsapp.model.EducationalLevels
import com.graduationproject.robokidsapp.model.Videos

class EntertainmentSectionFragment : Fragment() , VideoAdapter.OnItemClickListener {
    private var _binding: FragmentEntertainmentSectionBinding? = null
    private val binding get() = _binding!!
    private lateinit var mNavController: NavController
    private lateinit var videoList:ArrayList<Videos>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEntertainmentSectionBinding.inflate(inflater, container, false)


        videoList = ArrayList()
        videoList.add(Videos("", R.drawable.videos))
        videoList.add(Videos("", R.drawable.content))
        videoList.add(Videos("", R.drawable.educational))
        videoList.add(Videos("", R.drawable.entertaining))
        videoList.add(Videos("", R.drawable.videos))

        val adapter = VideoAdapter(requireContext(),videoList,this)
        binding.rvEntertainmentSection.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.rvEntertainmentSection.adapter = adapter
        binding.rvEntertainmentSection.setHasFixedSize(true)

        binding.rvEntertainmentSection.apply {
            set3DItem(true)
            setAlpha(true)
            setInfinite(true)
        }

        binding.entertainmentSectionBack.setOnClickListener {
            val action = EntertainmentSectionFragmentDirections.actionIntertainmentSectionFragmentToIntertainmentContentFragment()
            mNavController.navigate(action)
        }

        return binding.root
    }


    override fun onItemClick(position: Int) {
        val action = EntertainmentSectionFragmentDirections.actionIntertainmentSectionFragmentToIntertainmentVidoFramFragment()
        mNavController.navigate(action)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}