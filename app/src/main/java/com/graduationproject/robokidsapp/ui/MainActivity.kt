package com.graduationproject.robokidsapp.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.navigation.NavigationView
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.ActivityMainBinding
import com.graduationproject.robokidsapp.ui.parentsFragments.ParentsHomeFragmentDirections

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

        // this code is Permission
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            val permissions = arrayOf(android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
            ActivityCompat.requestPermissions(this, permissions,0)
        }

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
            R.id.myKids -> {
                val action = ParentsHomeFragmentDirections.actionParentsHomeFragmentToHomeKidsFragment()
                navController.navigate(action)
            }
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

