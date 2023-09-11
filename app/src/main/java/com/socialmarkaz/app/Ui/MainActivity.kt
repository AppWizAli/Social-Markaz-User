package com.socialmarkaz.app.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.socialmarkaz.app.Models.SearchHistoryDialogFragment
import com.socialmarkaz.app.R
import com.socialmarkaz.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() , SearchHistoryDialogFragment.SearchHistoryDialogListener
    {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_cart, R.id.navigation_search,R.id.navigation_myaccount
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

        override fun onSearchHistoryItemClick(query: String) {
            Toast.makeText(this, ""+query, Toast.LENGTH_SHORT).show()
        }
    }