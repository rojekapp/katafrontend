package com.paimon.katahack

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.nav_host_main)
        nav_view.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.navigation_status ||
                destination.id == R.id.navigation_mutasi ||
                destination.id == R.id.navigation_autotext
            ) {
                nav_view.visibility = View.VISIBLE
            } else {
                nav_view.visibility = View.GONE
            }
        }
    }


}
