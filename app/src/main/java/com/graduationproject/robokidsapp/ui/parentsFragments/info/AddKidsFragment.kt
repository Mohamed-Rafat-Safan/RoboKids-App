package com.graduationproject.robokidsapp.ui.parentsFragments.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.data.model.Child
import com.graduationproject.robokidsapp.data.model.Report
import com.graduationproject.robokidsapp.databinding.FragmentAddKidsBinding
import com.graduationproject.robokidsapp.util.Constants.Companion.ADD_KIDS
import com.graduationproject.robokidsapp.util.Constants.Companion.ADD_KIDS_FROM_HP
import com.graduationproject.robokidsapp.util.Constants.Companion.EDIT_KIDS
import com.graduationproject.robokidsapp.util.Resource
import com.graduationproject.robokidsapp.util.hide
import com.graduationproject.robokidsapp.util.show
import com.graduationproject.robokidsapp.util.toast
import dagger.hilt.android.AndroidEntryPoint
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class AddKidsFragment : Fragment() {
    private lateinit var mNavController: NavController
    private var _binding: FragmentAddKidsBinding? = null
    private val binding get() = _binding!!

    private var gender = "boy"

    private val args by navArgs<AddKidsFragmentArgs>()

    private val infoViewModel: InfoViewModel by viewModels()


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

        // this check operation that is editKids or not
        checkOperation()

        binding.ivSelectBoy.setOnClickListener {
            selectBoy()
        }

        binding.ivSelectGirl.setOnClickListener {
            selectGirl()
        }


        binding.ivSubKidsAge.setOnClickListener {
            var age: Int = binding.tvAgeKids.text.toString().toInt()
            if (age > 1) {
                age--
            }
            binding.tvAgeKids.text = age.toString()
        }


        binding.ivPlusKidsAge.setOnClickListener {
            var age: Int = binding.tvAgeKids.text.toString().toInt()
            if (age in 1..6) {
                age++
            }
            binding.tvAgeKids.text = age.toString()
        }

        binding.tvSkip.setOnClickListener {
            val action = AddKidsFragmentDirections.actionAddKidsFragmentToParentsHomeFragment()
            mNavController.navigate(action)
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // this listen to live data in viewModel (insertChild)
        observerInsertChild()

        // this listen to live data in viewModel (updateChild)
        observerUpdateChild()

        binding.btnAddKids.setOnClickListener {
            if (inputsValidation()) {
                if (args.operation == ADD_KIDS || args.operation == ADD_KIDS_FROM_HP) {
                    val child = getChildObj()

                    val listReports = getListReports()

                    infoViewModel.insertChild(child, listReports)
                } else {
                    val childUpdated = updateChildObj()
                    infoViewModel.updateChild(childUpdated)
                }
            }
        }
    }

    private fun getListReports():ArrayList<Report>{
        val list:ArrayList<Report> = ArrayList()
        var report:Report
        for (i in 0..6){
            val myDate = Date(System.currentTimeMillis() - (i * 1000 * 60 * 60 * 24))
            val f: Format = SimpleDateFormat("EEEE")
            val dayName: String = f.format(myDate)

            report = Report(dayDate = myDate, dayName = dayName)
            list.add(report)
        }
        return list
    }


    private fun observerInsertChild() {
        infoViewModel.insertChild.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.progressBarAddKids.show()
                }
                is Resource.Failure -> {
                    binding.progressBarAddKids.hide()
                    toast(resource.error)
                }
                is Resource.Success -> {
                    binding.progressBarAddKids.hide()
                    toast(resource.data)

                    val action =
                        AddKidsFragmentDirections.actionAddKidsFragmentToParentsHomeFragment()
                    mNavController.navigate(action)
                }
            }
        }
    }

    private fun observerUpdateChild() {
        infoViewModel.updateChild.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.progressBarAddKids.show()
                }
                is Resource.Failure -> {
                    binding.progressBarAddKids.hide()
                    toast(resource.error)
                }
                is Resource.Success -> {
                    binding.progressBarAddKids.hide()
                    toast(resource.data)

                    val action = AddKidsFragmentDirections.actionAddKidsFragmentToParentsHomeFragment()
                    mNavController.navigate(action)
                }
            }
        }
    }


    fun getChildObj(): Child {
        return Child(
            childName = binding.etChildName.text.toString(),
            childImage = 0,
            gender = gender,
            age = binding.tvAgeKids.text.toString().toInt()
        )
    }

    fun updateChildObj(): Child {
        return Child(
            id = args.currentChild?.id!!,
            childName = binding.etChildName.text.toString(),
            childImage = 0,
            gender = gender,
            age = binding.tvAgeKids.text.toString().toInt(),
            createDate = args.currentChild?.createDate!!
        )
    }


    private fun inputsValidation(): Boolean {
        binding.apply {
            val name = etChildName.text.toString().trim()
            if (name.isEmpty()) {
                etChildName.error = getString(R.string.select_name)
                etChildName.requestFocus()
                return false
            }
            return true
        }
    }


    private fun checkOperation() {
        if (args.operation == EDIT_KIDS) {
            val child = args.currentChild

            binding.apply {
                tvAddKidsTitle.text = getString(R.string.edit_kids)
                btnAddKids.text = getString(R.string.update_kids)
                tvSkip.hide()

                if (child?.gender == "boy") {
                    selectBoy()
                } else {
                    selectGirl()
                }

                etChildName.setText(child?.childName)
                tvAgeKids.text = child?.age.toString()
            }
        }

        if (args.operation == ADD_KIDS_FROM_HP) {
            binding.tvSkip.hide()
        }
    }


    private fun selectBoy() {
        gender = "boy"
        binding.apply {
            ivSelectBoy.setBackgroundResource(R.drawable.bg_select_gender)
            ivSelectGirl.setBackgroundResource(R.drawable.bg_select_gender_default)
            tvBoyName.setTextColor(resources.getColor(R.color.nameParent_color))
            tvGirlName.setTextColor(resources.getColor(R.color.black))
        }
    }

    private fun selectGirl() {
        gender = "girl"
        binding.apply {
            ivSelectGirl.setBackgroundResource(R.drawable.bg_select_gender)
            ivSelectBoy.setBackgroundResource(R.drawable.bg_select_gender_default)
            tvBoyName.setTextColor(resources.getColor(R.color.black))
            tvGirlName.setTextColor(resources.getColor(R.color.nameParent_color))
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}