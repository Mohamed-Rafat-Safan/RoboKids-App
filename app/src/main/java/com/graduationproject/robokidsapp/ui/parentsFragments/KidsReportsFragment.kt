package com.graduationproject.robokidsapp.ui.parentsFragments

import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.FragmentKidsReportsBinding

class KidsReportsFragment : Fragment() {
    private lateinit var mNavController: NavController
    private var _binding: FragmentKidsReportsBinding? = null
    private val binding get() = _binding!!


    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        mNavController = findNavController()

        //show status bar
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }


    lateinit var list1: ArrayList<BarEntry>
    lateinit var list2: ArrayList<BarEntry>
    lateinit var list3: ArrayList<BarEntry>
    lateinit var list4: ArrayList<BarEntry>
    lateinit var list5: ArrayList<BarEntry>
    lateinit var list6: ArrayList<BarEntry>
    lateinit var list7: ArrayList<BarEntry>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentKidsReportsBinding.inflate(inflater, container, false)

        getBarChartData()  // this add data to bar chart

        val barDataset1 = BarDataSet(list1, getString(R.string.sat))
        barDataset1.setColor(Color.GREEN)
        barDataset1.valueTextSize = 16f
        barDataset1.valueTextColor = R.color.black

        val barDataset2 = BarDataSet(list2, getString(R.string.sun))
        barDataset2.setColor(Color.RED)
        barDataset2.valueTextSize = 16f
        barDataset2.valueTextColor = R.color.black

        val barDataset3 = BarDataSet(list3, getString(R.string.mon))
        barDataset3.setColor(Color.YELLOW)
        barDataset3.valueTextSize = 16f
        barDataset3.valueTextColor = R.color.black

        val barDataset4 = BarDataSet(list4, getString(R.string.tues))
        barDataset4.setColor(Color.CYAN)
        barDataset4.valueTextSize = 16f
        barDataset4.valueTextColor = R.color.black

        val barDataset5 = BarDataSet(list5, getString(R.string.wed))
        barDataset5.setColor(Color.DKGRAY)
        barDataset5.valueTextSize = 16f
        barDataset5.valueTextColor = R.color.black

        val barDataset6 = BarDataSet(list6, getString(R.string.thurs))
        barDataset6.setColor(Color.BLUE)
        barDataset6.valueTextSize = 16f
        barDataset6.valueTextColor = R.color.black

        val barDataset7 = BarDataSet(list7, getString(R.string.fri))
        barDataset7.setColor(Color.MAGENTA)
        barDataset7.valueTextSize = 16f
        barDataset7.valueTextColor = R.color.black

        val bareData = BarData(barDataset1, barDataset2, barDataset3, barDataset4, barDataset5, barDataset6, barDataset7)
        binding.brDate.data = bareData

        binding.brDate.description.isEnabled = false

        binding.cardSat.setOnClickListener {
            val action = KidsReportsFragmentDirections.actionKidsReportsFragmentToTimesUseFragment("Sat")
            mNavController.navigate(action)
        }
        binding.cardSun.setOnClickListener {
            val action = KidsReportsFragmentDirections.actionKidsReportsFragmentToTimesUseFragment("Sun")
            mNavController.navigate(action)
        }
        binding.cardMon.setOnClickListener {
            val action = KidsReportsFragmentDirections.actionKidsReportsFragmentToTimesUseFragment("Mon")
            mNavController.navigate(action)
        }
        binding.cardTues.setOnClickListener {
            val action = KidsReportsFragmentDirections.actionKidsReportsFragmentToTimesUseFragment("Tues")
            mNavController.navigate(action)
        }
        binding.cardWed.setOnClickListener {
            val action = KidsReportsFragmentDirections.actionKidsReportsFragmentToTimesUseFragment("Wed")
            mNavController.navigate(action)
        }
        binding.cardThurs.setOnClickListener {
            val action = KidsReportsFragmentDirections.actionKidsReportsFragmentToTimesUseFragment("Thurs")
            mNavController.navigate(action)
        }
        binding.cardFri.setOnClickListener {
            val action = KidsReportsFragmentDirections.actionKidsReportsFragmentToTimesUseFragment("Fri")
            mNavController.navigate(action)
        }

        return binding.root
    }


    private fun getBarChartData() {
        list1 = ArrayList()
        list2 = ArrayList()
        list3 = ArrayList()
        list4 = ArrayList()
        list5 = ArrayList()
        list6 = ArrayList()
        list7 = ArrayList()
        list1.add(BarEntry(2f, 40f))
        list2.add(BarEntry(4f, 20f))
        list3.add(BarEntry(6f, 60f))
        list4.add(BarEntry(8f, 80f))
        list5.add(BarEntry(10f, 40f))
        list6.add(BarEntry(12f, 50f))
        list7.add(BarEntry(14f, 30f))
    }

}