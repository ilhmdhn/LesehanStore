package com.idnsoft.lesehanstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView

class TransactionListActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_list)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)

        bottomNavigation.selectedItemId = R.id.navigationListTransaction
        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigationHome -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }

            R.id.navigationTransactionOnProcces -> {
                startActivity(Intent(this, TransactionOnProgressActivity::class.java))
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