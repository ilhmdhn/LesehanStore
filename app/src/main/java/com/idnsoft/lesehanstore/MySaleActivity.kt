package com.idnsoft.lesehanstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.idnsoft.lesehanstore.adapter.MySaleListAdapter
import com.idnsoft.lesehanstore.connection.ApiEndPoint
import com.idnsoft.lesehanstore.model.Product
import kotlinx.android.synthetic.main.activity_my_sale.*
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MySaleActivity : AppCompatActivity() {

    private lateinit var rvPenting: RecyclerView
    var list: MutableList<Product> = ArrayList()
    var adapter: MySaleListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_sale)

        rvPenting = findViewById(R.id.rvMyItem)
        rvPenting.setHasFixedSize(true)
        btnAddProduct.setOnClickListener {
        showCardView()
        getMySaleData
        }
    }

    private fun showCardView() {
        rvPenting.layoutManager = LinearLayoutManager(this)
        val cardProductAdapter = MySaleListAdapter(list)
        rvPenting.adapter = cardProductAdapter

        cardProductAdapter.setOnItemClickCallback(object : MySaleListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Product) {
                selectedProduct(data)
            }
        })
    }


    //Read data product from database
    private val getMySaleData: Unit
        get() {

            AndroidNetworking.get(ApiEndPoint.MY_SALE)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(object : JSONArrayRequestListener {
                    override fun onResponse(response: JSONArray) {
                        list.clear()
                        for (i in 0 until response.length()) {
                            try {
                                val getData = Product()
                                val jsonObject = response.getJSONObject(i)
                                getData.productName = jsonObject.getString("productName")
                                getData.asalBarang = jsonObject.getString("asalBarang")
                                getData.productMaterial = jsonObject.getString("productMaterial")
                                getData.productDescription = jsonObject.getString("productDescription")
                                getData.productStock = jsonObject.getInt("productStock")
                                getData.productColor = jsonObject.getString("productColor")
                                getData.productPrice = jsonObject.getInt("productPrice")
                                getData.productPhoto = jsonObject.getString("productPhoto")
                                list.add(getData)
                                adapter = MySaleListAdapter(list)
                            } catch (e: JSONException) {
                                e.printStackTrace()
                                Toast.makeText(this@MySaleActivity,
                                    "gagal menampilkan data",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onError(error: ANError) {
                        Toast.makeText(this@MySaleActivity,
                            "tidak ada koneksi internet",
                            Toast.LENGTH_SHORT).show()
                    }
                })
        }



    private fun selectedProduct(product: Product) {
        val moveIntent = Intent(this, ProductDetailActivity::class.java)
        moveIntent.putExtra(ProductDetailActivity.EXTRA_PRODUCT, product)
        startActivity(moveIntent)
    }
}