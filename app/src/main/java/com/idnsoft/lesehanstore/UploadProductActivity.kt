package com.idnsoft.lesehanstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.idnsoft.lesehanstore.connection.ApiEndPoint
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_upload_product.*
import org.json.JSONObject

class UploadProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_product)

        btnUploadProduct.setOnClickListener {
            uploadProduct()
        }
    }

    private fun uploadProduct() {

        var isValidRegister = false

        val productName = edtProductName.text.toString().trim()
        val productMaterial = edtProductMaterial.text.toString().trim()
        val productColor = edtProductColor.text.toString().trim()
        val productOrigin = edtProductOrigin.text.toString().trim()
        val productDescription = edtProductDescription.text.toString().trim()
        val productPrice = edtProductPrice.text.toString().trim()
        val uploadUser = edtIdUser.text.toString().trim()

        when {
            productName.isEmpty() ->{
                isValidRegister = true
                edtProductName.error = "Field ini tidak boleh kosong"
            }

            productMaterial.isEmpty()->{
                isValidRegister = true
                edtProductMaterial.error = "Field ini tidak boleh kosong"
            }

            productColor.isEmpty() ->{
                isValidRegister = true
                edtProductColor.error = "Field ini tidak boleh kosong"
            }


            productOrigin.isEmpty() ->{
                isValidRegister = true
                edtProductOrigin.error = "Field ini tidak boleh kosong"
            }

            productDescription.isEmpty() ->{
                isValidRegister = true
                edtProductDescription.error = "Field ini tidak boleh kosong"
            }

            productPrice.isEmpty() ->{
                isValidRegister = true
                edtProductPrice.error = "Field ini tidak boleh kosong"
            }

            uploadUser.isEmpty() ->{
                isValidRegister = true
                edtIdUser.error = "Field ini tidak boleh kosong"
            }


        }

        if (!isValidRegister) {
            AndroidNetworking.post(ApiEndPoint.ADD_PRODUCT)
                .addBodyParameter("nama_barang", productName)
                .addBodyParameter("bahan", productMaterial)
                .addBodyParameter("warna", productColor)
                .addBodyParameter("asal", productOrigin)
                .addBodyParameter("deskripsi", productDescription)
                .addBodyParameter("harga", productPrice)
                .addBodyParameter("id_user", uploadUser)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {

                    override fun onResponse(response: JSONObject?) {

                        Toast.makeText(applicationContext,
                            response?.getString("message"),
                            Toast.LENGTH_LONG).show()

                        if (response?.getString("message")?.contains("successfully")!!) {
//                            this@RegisterActivity.finish()
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