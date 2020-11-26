package com.idnsoft.lesehanstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.idnsoft.lesehanstore.converter.getCurrency
import com.idnsoft.lesehanstore.model.Product
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_PRODUCT = "extra_product"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val product = intent.getParcelableExtra<Product>(EXTRA_PRODUCT) as Product

        tvProductName.text=product.productName
        tvPrice.text= getCurrency(product.productPrice)
        tvDetail.text=product.productDescription
        tvProductOrigin.text=product.asalBarang
        tvProductMaterial.text=product.productMaterial
        tvEmail.text=product.sellerEmail
        tvProductColor.text=product.productColor
        tvPhoneNumber.text=product.sellerContact
        tvProductStock.text=product.productStock.toString()
        tvSellerName.text=product.sellerName
        tvDetail.text=product.productDescription
        Glide.with(this)
            .load(product.productPhoto)
            .into(imgProduct)
        Glide.with(this)
            .load(product.sellerPhoto)
            .into(imgSellerPhoto)
    }
}