package com.graduationproject.robokidsapp.ui

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.navigation.NavigationView
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.ActivityMainBinding
import com.graduationproject.robokidsapp.ui.fragments.ParentsHomeFragmentDirections

//                              بسم الله الرحمن الرحيم
class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {
    companion object{
        lateinit var binding:ActivityMainBinding
    }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Hide status bar
        this.window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController


        setSupportActionBar(binding.customToolbarMainActivity)

        binding.drawLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)


        // لكي يستدعي الداله الي تحت الي اسمها  onNavigationItemSelected
        binding.navigationView.setNavigationItemSelectedListener(this)



        val actionToggle = ActionBarDrawerToggle(this, binding.drawLayout , binding.customToolbarMainActivity ,
            R.string.draw_open , R.string.draw_close)

        binding.drawLayout.addDrawerListener(actionToggle)

        actionToggle.syncState()

    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.myKids -> showToast(item.title.toString())
            R.id.knowApp -> {
                val action = ParentsHomeFragmentDirections.actionParentsHomeFragmentToGetToKnowTheAppFragment2()
                navController.navigate(action)
            }
            R.id.commonQuestions -> {
                val action = ParentsHomeFragmentDirections.actionParentsHomeFragmentToCommonQuestionsFragment()
                navController.navigate(action)
            }
            R.id.setting -> {
                val action = ParentsHomeFragmentDirections.actionParentsHomeFragmentToSettingFragment()
                navController.navigate(action)
            }
            R.id.signOut -> {
                val action = ParentsHomeFragmentDirections.actionParentsHomeFragmentToWelcomeFragment()
                navController.navigate(action)
            }

        }
        closeDrawer() // this method to end or hide the Navigation drawer
        return true
    }

    fun showToast(str:String){
        Toast.makeText(this, str ,Toast.LENGTH_LONG).show()
    }

    fun closeDrawer(){
        binding.drawLayout.closeDrawer(GravityCompat.START)
    }

    // هذه الدله علشان لو انت كنت فاتح ال navigation drawer ضغط علي زر الرجوع ميخرجش من التطبيق كاكل
    // لا هي لو مفتوحه هيعملها close عادي ومش هيخرجك من التطبيق
    override fun onBackPressed() {
        if(binding.drawLayout.isDrawerOpen(GravityCompat.START))
            closeDrawer()
        else
            super.onBackPressed()
    }


}

