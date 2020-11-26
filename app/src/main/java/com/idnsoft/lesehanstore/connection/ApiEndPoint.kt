package com.idnsoft.lesehanstore.connection

class ApiEndPoint {
    companion object{
    private val SERVER="http://192.168.0.248/lesehan/"
    val READ_PRODUCT = SERVER +"readproduk.php"
    val CREATE_USER = SERVER + "createuser.php"
    val ADD_PRODUCT = SERVER + "addProduct.php"
    val MY_SALE = SERVER + "jualanku/readjualanku.php"
    }
}