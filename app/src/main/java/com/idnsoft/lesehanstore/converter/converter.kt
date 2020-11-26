package com.idnsoft.lesehanstore.converter

import java.text.DecimalFormat

fun getCurrency(price: Int) : String{
    val format = DecimalFormat("#,###,###")
    return "Rp. "+format.format(price).replace(",".toRegex(), ".")
}