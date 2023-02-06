package com.graduationproject.robokidsapp.ui.parentsFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.CalendarView.OnDateChangeListener
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.FragmentUpdateAccountBinding
import java.util.*

class UpdateAccountFragment : Fragment() {
    private lateinit var mNavController: NavController
    private var _binding: FragmentUpdateAccountBinding? = null
    private val binding get() = _binding!!

    companion object{
        var mDay = 0
        var mMonth = 0
        var mYear = 0
    }

    var flagBirth = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateAccountBinding.inflate(inflater, container, false)

        binding.selectYearBirth.setOnClickListener { showDialogCountries()  }

        binding.btnSaveParentData.setOnClickListener {  }

        binding.ivFather.setOnClickListener {
            binding.ivFather.setBackgroundResource(R.drawable.bg_select_gender)
            binding.ivMother.setBackgroundResource(R.drawable.bg_select_gender_default)
            binding.tvNameFather.setTextColor(resources.getColor(R.color.nameParent_color))
            binding.tvNameMother.setTextColor(resources.getColor(R.color.black))
        }

        binding.ivMother.setOnClickListener {
            binding.ivMother.setBackgroundResource(R.drawable.bg_select_gender)
            binding.ivFather.setBackgroundResource(R.drawable.bg_select_gender_default)
            binding.tvNameMother.setTextColor(resources.getColor(R.color.nameParent_color))
            binding.tvNameFather.setTextColor(resources.getColor(R.color.black))
        }

        binding.ivLeftBack.setOnClickListener {
            mNavController.currentBackStackEntry?.let { backEntry -> mNavController.popBackStack(backEntry.destination.id,true) }
        }


        return binding.root
    }



    fun showDialogCountries() {
        val customView = LayoutInflater.from(activity)
            .inflate(R.layout.custom_layout_select_year_birth, null, false)


        val dialog = AlertDialog.Builder(requireContext())


        val calenderBirth = customView.findViewById<CalendarView>(R.id.calenderBirth!!)
        calenderBirth.setOnDateChangeListener(object :OnDateChangeListener{
            override fun onSelectedDayChange(p0: CalendarView, year: Int, month: Int, dayOfMonth: Int) {
                mDay = dayOfMonth
                mMonth = month+1
                mYear = year
                flagBirth = true
                val birth = "$dayOfMonth/${month+1}/$year"
                binding.tvBirth.text = birth
            }
        })


        if(flagBirth){
            var calendar = Calendar.getInstance()
            calendar.set(Calendar.DAY_OF_MONTH, mDay)
            calendar.set(Calendar.YEAR, mYear-1)
            calendar.add(Calendar.MONTH, mMonth+1 )
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