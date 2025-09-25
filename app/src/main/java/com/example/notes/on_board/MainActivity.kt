package com.example.notes.on_board

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.notes.R
import com.example.notes.data.local.pref.Pref

class MainActivity : AppCompatActivity() {

    private lateinit var pref: Pref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        pref = Pref (this)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        if (pref.getUserSeen()){
            navController.navigate(R.id.mainFragment)
        } else{
            navController.navigate(R.id.onBoardFragment)
        }
        }
    }
