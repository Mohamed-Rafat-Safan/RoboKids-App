package com.graduationproject.robokidsapp.ui.parentsFragments

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.adapters.ReportsKidsAdapter
import com.graduationproject.robokidsapp.databinding.FragmentParentsHomeBinding
import com.graduationproject.robokidsapp.model.Child
import com.graduationproject.robokidsapp.ui.MainActivity

class ParentsHomeFragment : Fragment() , ReportsKidsAdapter.OnItemClickListener{
    companion object{
        lateinit var mNavController: NavController
    }

    private var _binding: FragmentParentsHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var listChild:ArrayList<Child>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()


        MainActivity.binding.drawLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentParentsHomeBinding.inflate(inflater, container, false)

        binding.addNewKids.setOnClickListener {
            val action = ParentsHomeFragmentDirections.actionParentsHomeFragmentToAddKidsFragment("addKids")
            mNavController.navigate(action)
        }


        listChild = ArrayList()
        listChild.add(Child("محمد" , R.drawable.boy2))
        listChild.add(Child("سلمي" , R.drawable.boy2))
        listChild.add(Child("أحمد" , R.drawable.boy2))
        listChild.add(Child("علي" , R.drawable.boy2))
        listChild.add(Child("شروق" , R.drawable.boy2))


        val adapter = ReportsKidsAdapter(requireContext() , listChild , this)
        binding.rvReportsKids.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        binding.rvReportsKids.adapter = adapter
        binding.rvReportsKids.setHasFixedSize(true)

        return binding.root
    }


    override fun onResume() {
        super.onResume()
        MainActivity.binding.customToolbarMainActivity.visibility = View.VISIBLE
        MainActivity.binding.customToolbarMainActivity.title = ""

        //show status bar
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        activity?.window!!.statusBarColor = this.resources.getColor(R.color.teal_700)

        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onDestroyView() {
        super.onDestroyView()
        MainActivity.binding.customToolbarMainActivity.visibility = View.GONE
        _binding = null
    }



    override fun onItemClick(position: Int) {

    }

}