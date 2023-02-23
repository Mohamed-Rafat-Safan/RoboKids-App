package com.graduationproject.robokidsapp.ui.kidsFragments

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.graduationproject.robokidsapp.data.model.Report
import com.graduationproject.robokidsapp.databinding.FragmentContentBinding
import com.graduationproject.robokidsapp.ui.parentsFragments.info.InfoViewModel
import com.graduationproject.robokidsapp.util.Constants.Companion.EDUCATIONAL_FLAG
import com.graduationproject.robokidsapp.util.Constants.Companion.ENTERTAINMENT_FLAG
import com.graduationproject.robokidsapp.util.Resource
import com.graduationproject.robokidsapp.util.toast
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class ContentFragment : Fragment() {

    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!
    private lateinit var mNavController: NavController
    private val args by navArgs<ContentFragmentArgs>()

    private lateinit var listReports: ArrayList<Report>

    private var contentFlag = ""

    private val infoViewModel: InfoViewModel by viewModels()


    override fun onResume() {
        super.onResume()
        //Hide status bar
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE


        // get all reports from firebase
        infoViewModel.getReports(args.currentChild.id)
        observerGetReports()

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
        _binding = FragmentContentBinding.inflate(inflater, container, false)

        listReports = ArrayList()


        //get data from Home_Kids
        binding.tvContentChildName.text = args.currentChild.childName
        binding.ivChild.setImageResource(args.currentChild.childImage)


        binding.educationalContent.setOnClickListener {
            val action = ContentFragmentDirections.actionContentFragmentToEducationalContentFragment(args.currentChild)
            mNavController.navigate(action)
            contentFlag = EDUCATIONAL_FLAG
        }

        binding.entertainmentContent.setOnClickListener {
            val action =
                ContentFragmentDirections.actionContentFragmentToIntertainmentContentFragment(args.currentChild)
            mNavController.navigate(action)
            contentFlag = ENTERTAINMENT_FLAG
        }

        binding.back.setOnClickListener {
            mNavController.currentBackStackEntry?.let { backEntry ->
                mNavController.popBackStack(
                    backEntry.destination.id,
                    true
                )
            }
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    private fun updateReportsForContents() {
        val date = Date()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val dayDate = dateFormat.format(date)

        val dayNameFormat = SimpleDateFormat("EEEE")
        val dayName: String = dayNameFormat.format(date)


        for (report in listReports) {
            if (report.dayName == dayName) {
                val dayDateSorted = dateFormat.format(report.dayDate)
                if (dayDate == dayDateSorted) {
                    checkContent(report, true)
                } else {
                    checkContent(report, false)
                }
                break
            }
        }
    }


    private fun checkContent(report: Report, isEqual: Boolean) {
        if (contentFlag == EDUCATIONAL_FLAG) {
            val reportUpdated = getObjReport(isEqual, report)
            infoViewModel.updateReports(args.currentChild.id, report.id, reportUpdated)
            observeUpdateReport()
        } else if(contentFlag == ENTERTAINMENT_FLAG) {
            val reportUpdated = getObjReport(isEqual, report)
            infoViewModel.updateReports(args.currentChild.id, report.id, reportUpdated)
            observeUpdateReport()
        }
        EducationalContentFragment.educationalTime = 0
        EntertainmentContentFragment.entertainmentTime = 0
    }


    private fun getObjReport(isEqual: Boolean, report: Report): Report {
        if (isEqual && contentFlag == EDUCATIONAL_FLAG) {
            return Report(
                id = report.id,
                dayName = report.dayName,
                dayDate = report.dayDate,
                educationalTime = EducationalContentFragment.educationalTime.toString(),
                entertainmentTime = report.entertainmentTime,
                totalTime = report.totalTime,
                notifications = report.notifications
            )
        } else if (!isEqual && contentFlag == EDUCATIONAL_FLAG) {
            return Report(
                id = report.id,
                dayName = report.dayName,
                dayDate = Date(),
                educationalTime = EducationalContentFragment.educationalTime.toString(),
                entertainmentTime = "",
                totalTime = "",
                notifications = arrayListOf()
            )
        } else if (isEqual && contentFlag == ENTERTAINMENT_FLAG) {
            return Report(
                id = report.id,
                dayName = report.dayName,
                dayDate = report.dayDate,
                educationalTime = report.educationalTime,
                entertainmentTime = EntertainmentContentFragment.entertainmentTime.toString(),
                totalTime = report.totalTime,
                notifications = report.notifications
            )
        } else {
            return Report(
                id = report.id,
                dayName = report.dayName,
                dayDate = Date(),
                educationalTime = "",
                entertainmentTime = EntertainmentContentFragment.entertainmentTime.toString(),
                totalTime = "",
                notifications = arrayListOf()
            )
        }
    }


    private fun observerGetReports() {
        infoViewModel.getReports.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {}
                is Resource.Failure -> {
                    toast(resource.error)
                }
                is Resource.Success -> {
                    listReports = resource.data

                    toast(""+listReports.size)
                    // this get new reports and update in firebase
                    updateReportsForContents()
                }
            }
        }
    }

    private fun observeUpdateReport() {
        infoViewModel.updateReports.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {}
                is Resource.Failure -> {
                    toast(resource.error)
                }
                is Resource.Success -> {
                    toast(resource.data)
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}