package com.idnsoft.lesehanstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView

class TransactionOnProgressActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_on_progress)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)

        bottomNavigation.selectedItemId = R.id.navigationTransactionOnProcces
        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigationHome-> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }

            R.id.navigationListTransaction -> {
                startActivity(Intent(this, TransactionListActivity::class.java))
                finish()
            }

            R.id.navigationProfile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
                finish()
            }
        }
        return true
    }
}