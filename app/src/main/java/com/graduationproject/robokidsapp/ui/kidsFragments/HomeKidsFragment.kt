package com.graduationproject.robokidsapp.ui.kidsFragments

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.adapters.ChildsAdapter
import com.graduationproject.robokidsapp.databinding.FragmentHomeKidsBinding
import com.graduationproject.robokidsapp.model.Child


class HomeKidsFragment : Fragment(),ChildsAdapter.OnItemClickListener {

    private var _binding: FragmentHomeKidsBinding? = null
    private val binding get() = _binding!!
    private lateinit var mNavController: NavController
    private lateinit var childList:ArrayList<Child>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
    }

    override fun onResume() {
        super.onResume()
        //Hide status bar
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeKidsBinding.inflate(inflater, container, false)

        binding.imgParent.setOnClickListener {
            goToParentHome()
        }
        binding.tvParentName.setOnClickListener {
            goToParentHome()
        }

        childList = ArrayList<Child>()
        childList.add(Child("mohamed",R.drawable.boy2))
        childList.add(Child("Ali",R.drawable.boy2))
        childList.add(Child("Ahmed",R.drawable.boy2))

        val adapter = ChildsAdapter(requireContext(),childList,this)
        binding.rvKids.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.rvKids.adapter = adapter
        binding.rvKids.setHasFixedSize(true)

        return binding.root
    }

    fun goToParentHome(){
        val action = HomeKidsFragmentDirections.actionHomeKidsFragmentToParentsHomeFragment()
        mNavController.navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int) {
        val child = childList[position]
        val action = HomeKidsFragmentDirections.actionHomeKidsFragmentToContentFragment(child)
        mNavController.navigate(action)
    }

}