package com.graduationproject.robokidsapp.ui.parentsFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.FragmentParentsDataBinding
import java.util.*


class ParentsDataFragment : Fragment() {
    private var _binding: FragmentParentsDataBinding? = null
    private val binding get() = _binding!!

    companion object{
        var mDay = 0
        var mMonth = 0
        var mYear = 0
    }

    var flagBirth = false


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
        _binding = FragmentParentsDataBinding.inflate(inflater, container, false)

        binding.btnParentData.setOnClickListener {
            val action = ParentsDataFragmentDirections.actionParentsDataFragmentToAddKidsFragment("addKids")
            mNavController.navigate(action)
        }

        binding.ivFather.setOnClickListener {
            binding.ivFather.setBackgroundResource(R.drawable.bg_select_gender)
            binding.ivMother.setBackgroundResource(0)
        }

        binding.ivMother.setOnClickListener {
            binding.ivFather.setBackgroundResource(0)
            binding.ivMother.setBackgroundResource(R.drawable.bg_select_gender)
        }

        binding.selectYearBirth.setOnClickListener {
            showDialogCountries()
        }

        return binding.root
    }

    fun showDialogCountries() {
        val customView = LayoutInflater.from(activity)
            .inflate(R.layout.custom_layout_select_year_birth, null, false)

        val dialog = AlertDialog.Builder(requireContext())


        val calenderBirth = customView.findViewById<CalendarView>(R.id.calenderBirth!!)
        calenderBirth.setOnDateChangeListener(object : CalendarView.OnDateChangeListener {
            override fun onSelectedDayChange(p0: CalendarView, year: Int, month: Int, dayOfMonth: Int) {
                UpdateAccountFragment.mDay = dayOfMonth
                UpdateAccountFragment.mMonth = month+1
                UpdateAccountFragment.mYear = year
                flagBirth = true
                val birth = "$dayOfMonth/${month+1}/$year"
                binding.tvBirth.text = birth
            }
        })


        if(flagBirth){
            var calendar = Calendar.getInstance()
            calendar.set(Calendar.DAY_OF_MONTH, UpdateAccountFragment.mDay)
            calendar.set(Calendar.YEAR, UpdateAccountFragment.mYear -1)
            calendar.add(Calendar.MONTH, UpdateAccountFragment.mMonth +1 )
            calenderBirth.setDate(calendar.timeInMillis, true, true)
        }


        dialog.setView(customView)

        val alert = dialog.create()
        alert.show()

        val close = customView.findViewById<ImageView>(R.id.iv_close)

        close.setOnClickListener { alert.dismiss() } // this to close dialog

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}