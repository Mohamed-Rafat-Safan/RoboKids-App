package com.graduationproject.robokidsapp.ui.parentsFragments

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.graduationproject.robokidsapp.R
import kotlinx.coroutines.*

class SplashFragment : Fragment() {
    private lateinit var mNavController:NavController

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }


    override fun onResume() {
        super.onResume()
        //Hide status bar
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
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
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch {
            delay(2000)
            withContext(Dispatchers.Main){
                if (checkLoggedIn()){
                    val action = SplashFragmentDirections.actionSplashFragmentToHomeKidsFragment()
                    mNavController.navigate(action)
                }else{
                    val action = SplashFragmentDirections.actionSplashFragmentToParentsORKidsFragment()
                    mNavController.navigate(action)
                }

            }
        }
    }


    private fun checkLoggedIn():Boolean {
        return auth.currentUser != null
    }

}