package com.graduationproject.robokidsapp.ui

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.navigation.NavigationView
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.ActivityMainBinding

//                              بسم الله الرحمن الرحيم
class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {
    companion object{
        lateinit var binding:ActivityMainBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController


        setSupportActionBar(binding.customToolbarMainActivity)

        // لكي يستدعي الداله الي تحت الي اسمها  onNavigationItemSelected
        binding.navigationView.setNavigationItemSelectedListener(this)


        val actionToggle = ActionBarDrawerToggle(this, binding.drawLayout , binding.customToolbarMainActivity ,
            R.string.draw_open , R.string.draw_close)

        binding.drawLayout.addDrawerListener(actionToggle)

        actionToggle.syncState()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this, item.title ,Toast.LENGTH_LONG).show()
        closeDrawer() // this method to end or hide the Navigation drawer
        return true
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
