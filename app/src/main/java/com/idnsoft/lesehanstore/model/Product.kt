package com.idnsoft.lesehanstore.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.text.DecimalFormat

@Parcelize
data class Product(
    var productId: Int=0,
    var productName: String="",
    var asalBarang: String="",
    var productMaterial: String="",
    var productDescription: String="",
    var productColor: String="",
    var productPrice: Int=0,
    var productStock: Int=0,
    var productPhoto: String ="",
    var sellerName: String="",
    var sellerEmail: String="",
    var sellerContact: String="",
    var sellerAddress: String="",
    var sellerPhoto: String =""
): Parcelable