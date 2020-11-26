package com.idnsoft.lesehanstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.idnsoft.lesehanstore.adapter.ProductMenuAdapter
import com.idnsoft.lesehanstore.connection.ApiEndPoint
import com.idnsoft.lesehanstore.model.Product
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var rvProduk : RecyclerView
    var list: MutableList<Product> = ArrayList()
    var mainAdapter: ProductMenuAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvProduk = findViewById(R.id.rv_produk)
        rvProduk.setHasFixedSize(true)

        showRecyclerGrid()
        getproductData

        bottomNavigation.selectedItemId = R.id.navigationHome
        bottomNavigation.setOnNavigationItemSelectedListener(this)

    }



    //showRecyclerGrid
    private fun showRecyclerGrid(){
        rvProduk.layoutManager = GridLayoutManager(this,2)
        val gridProductAdapter = ProductMenuAdapter(list)
        rvProduk.adapter = gridProductAdapter

        gridProductAdapter.setOnItemClickCallback(object: ProductMenuAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Product) {
                selectedProduct(data)
            }
        })
    }


    //Read data product from database
    private val getproductData: Unit
        get() {
            AndroidNetworking.get(ApiEndPoint.READ_PRODUCT)
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
                                getData.sellerName = jsonObject.getString("sellerName")
                                getData.sellerEmail = jsonObject.getString("sellerEmail")
                                getData.sellerAddress = jsonObject.getString("sellerAddress")
                                getData.sellerContact = jsonObject.getString("sellerContact")
                                getData.sellerPhoto = jsonObject.getString("sellerPhoto")
                                list.add(getData)
                                mainAdapter = ProductMenuAdapter(list)
                            } catch (e: JSONException) {
                                e.printStackTrace()
                                Toast.makeText(this@MainActivity, "gagal menampilkan data", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onError(error: ANError) {
                        Toast.makeText(this@MainActivity, "tidak ada koneksi internet", Toast.LENGTH_SHORT).show()
                    }
                })
        }


    //click selected product
    private fun selectedProduct(product: Product) {
        val moveIntent = Intent(this, ProductDetailActivity::class.java)
        moveIntent.putExtra(ProductDetailActivity.EXTRA_PRODUCT, product)
        startActivity(moveIntent)
    }

    //bottom navigation
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigationTransactionOnProcces -> {
                startActivity(Intent(this, TransactionOnProgressActivity::class.java))
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