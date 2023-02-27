package com.graduationproject.robokidsapp.ui.kidsFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.adapters.ContentAdapter
import com.graduationproject.robokidsapp.databinding.FragmentEducationalContentBinding
import com.graduationproject.robokidsapp.data.model.Content
import com.graduationproject.robokidsapp.ui.parentsFragments.info.InfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date

@AndroidEntryPoint
class EducationalContentFragment : Fragment(), ContentAdapter.OnItemClickListener {

    private var _binding: FragmentEducationalContentBinding? = null
    private val binding get() = _binding!!
    private lateinit var mNavController: NavController
    private lateinit var listContent: ArrayList<Content>

    private val args by navArgs<EducationalContentFragmentArgs>()

    lateinit var startDate: Date
    lateinit var endDate: Date


    private val infoViewModel: InfoViewModel by viewModels()

    companion object{
        var educationalTime = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()

        startDate = Date()
        endDate = Date()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentEducationalContentBinding.inflate(inflater, container, false)


        binding.educationalContentBack.setOnClickListener {
            mNavController.currentBackStackEntry?.let { backEntry ->
                mNavController.popBackStack(backEntry.destination.id, true)
            }
        }

        listContent = ArrayList()
        listContent.add(Content("Pronunciation", R.drawable.main_pronounce))
        listContent.add(Content("Board", R.drawable.main_board))
        listContent.add(Content("Questions", R.drawable.solving_problems))


        val adapter = ContentAdapter(requireContext(), listContent, this)
        binding.rvEducationContent.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvEducationContent.adapter = adapter
        binding.rvEducationContent.setHasFixedSize(true)


        return binding.root
    }


    override fun onItemClick(position: Int) {
        val content = listContent[position]
        val action = EducationalContentFragmentDirections.actionEducationalContentFragmentToEducationalSectionFragment(content.contentName)
        mNavController.navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onDestroy() {
        super.onDestroy()
        endDate = Date()

        val hourFormat = SimpleDateFormat("HH")
        val munitFormat = SimpleDateFormat("mm")
        val secondFormat = SimpleDateFormat("ss")

        val startHour = hourFormat.format(startDate).toInt()
        val startMunit = munitFormat.format(startDate).toInt()
        val startSecond = secondFormat.format(startDate).toInt()

        val endHour = hourFormat.format(endDate).toInt()
        val endMunit = munitFormat.format(endDate).toInt()
        val endSecond = secondFormat.format(endDate).toInt()

        val totalHour = endHour - startHour
        val totalMunit = endMunit - startMunit
        val totalSecond = endSecond - startSecond

        educationalTime = (totalHour * 60) + totalMunit + (totalSecond / 60)
    }

}