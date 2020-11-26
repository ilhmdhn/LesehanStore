package com.idnsoft.lesehanstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigation.selectedItemId = R.id.navigationProfile
        bottomNavigation.setOnNavigationItemSelectedListener(this)

        uploadProduct.setOnClickListener{
            startActivity(Intent(this,UploadProductActivity::class.java))
        }
        btnMySale.setOnClickListener{
            startActivity(Intent(this,MySaleActivity::class.java))
        }
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

            R.id.navigationListTransaction -> {
                startActivity(Intent(this, TransactionListActivity::class.java))
                finish()
            }
        }
        return true
    }
}