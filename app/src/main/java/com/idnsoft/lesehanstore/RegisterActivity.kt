package com.idnsoft.lesehanstore

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.idnsoft.lesehanstore.connection.ApiEndPoint
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject
import java.io.File


class RegisterActivity : AppCompatActivity() {

    private var selectedImage = null

    companion object {
        private const val REQUEST_CODE_IMAGE_PICKER = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister.setOnClickListener {
            create()
        }

        tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun create() {

        var isValidRegister = false

        val username = edtUsername.text.toString().trim()
        val name = edtName.text.toString().trim()
        val email = edtEmail.text.toString().trim()
        val phone = edtPhone.text.toString().trim()
        val password = edtPassword.text.toString().trim()
        val passwordValidation = edtPasswordConfirmation.text.toString().trim()
        val address = edtAddress.text.toString().trim()

        when {
            username.isEmpty() ->{
                isValidRegister = true
                edtUsername.error = "Field ini tidak boleh kosong"
            }

            name.isEmpty()->{
                isValidRegister = true
                edtName.error = "Field ini tidak boleh kosong"
            }

            email.isEmpty() ->{
                isValidRegister = true
                edtEmail.error = "Field ini tidak boleh kosong"
            }


            password.isEmpty() ->{
                isValidRegister = true
                edtPassword.error = "Field ini tidak boleh kosong"
            }

            passwordValidation.isEmpty() ->{
                isValidRegister = true
                edtPasswordConfirmation.error = "Field ini tidak boleh kosong"
            }

            phone.isEmpty() ->{
                isValidRegister = true
                edtPhone.error = "Field ini tidak boleh kosong"
            }
            
            address.isEmpty() ->{
                isValidRegister = true
                edtAddress.error = "Field ini tidak boleh kosong"
            }

            password != passwordValidation->{
                isValidRegister = true
                edtPassword.error = "Password harus sama"
                edtPasswordConfirmation.error = "Password harus sama"
            }

        }

        if (!isValidRegister) {


            AndroidNetworking.post(ApiEndPoint.CREATE_USER)
                .addBodyParameter("username", edtUsername.text.toString())
                .addBodyParameter("nama", edtName.text.toString())
                .addBodyParameter("email", edtEmail.text.toString())
                .addBodyParameter("kontak", edtPhone.text.toString())
                .addBodyParameter("password", edtPassword.text.toString())
                .addBodyParameter("alamat", edtAddress.text.toString())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {

                    override fun onResponse(response: JSONObject?) {

                        Toast.makeText(applicationContext,
                            response?.getString("message"),
                            Toast.LENGTH_LONG).show()

                        if (response?.getString("message")?.contains("successfully")!!) {
                            this@RegisterActivity.finish()
                        }

                    }

                    override fun onError(anError: ANError?) {
                        Log.d("ONERROR", anError?.errorDetail?.toString())
                        Toast.makeText(applicationContext, "Connection Failure", Toast.LENGTH_SHORT)
                            .show()
                    }


                })

        }

    }
}
