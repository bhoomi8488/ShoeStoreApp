package com.example.shoestore

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.shoestore.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var navController: NavController

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment

        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)


        //toolbar.inflateMenu(R.menu.shoe_menu)
        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, bundle: Bundle? ->
            if (nd.id == nc.graph.startDestination) {
                Log.i("mode==", "UNlocked")
                navController.popBackStack(R.id.action_loginFragment_to_welcomeFragment, true)
                navController.popBackStack(R.id.action_loginFragment_to_listFragment, true)
            }
        }
        //toolbar.inflateMenu(R.menu.shoe_menu)
        setSupportActionBar(toolbar);

        toolbar.setupWithNavController(navController, appBarConfiguration)
        //NavigationUI.setupWithNavController(toolbar, navController)
        //  NavigationUI.setupActionBarWithNavController(this@MainActivity, navController, appBarConfiguration)

    }

    fun savePreference() {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putBoolean("Status", false)
            putString("Email", "")
            putString("Password", "")
            putString("detail", "")
            apply()
        }

    }
}