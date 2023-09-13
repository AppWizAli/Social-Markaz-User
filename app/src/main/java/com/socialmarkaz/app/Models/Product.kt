package com.socialmarkaz.app.Models

import java.io.Serializable


data class Product @JvmOverloads constructor(
    var productName: String = "",
    var price: String = "0",
    var selerName: String = "",
    var productPhoto: String = "",
    var discount: String = "",
    var type:String="",
    var catagory:String="",
    var deliverType:String="",
    var quantity:String="",
    var storename:String="",
    var storeId:String="",
    var cartType:String="",
    var docId:String=""

): Serializable