package com.idnsoft.lesehanstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener(this)
        tvSignUp.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnLogin->{
                val moveMain = Intent(this, MainActivity::class.java)
                startActivity(moveMain)
                finish()
            }
            R.id.tvSignUp->{
                startActivity(Intent(this,RegisterActivity::class.java))
            }
        }

    }

}