package com.example.bottomnavigationviewkotlin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.hide()
        val bottomView = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        replaceWithFragment(HomeFragment())
        // by default, home fragment should be visible to the user

        bottomView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> replaceWithFragment(HomeFragment())
                R.id.search -> replaceWithFragment(SearchFragment())
                R.id.profile -> replaceWithFragment(ProfileFragment())
                else -> {

                }
            }
            true

        }
    }

    private fun replaceWithFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}