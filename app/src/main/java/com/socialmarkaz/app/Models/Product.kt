package com.socialmarkaz.app.Models



data class Product @JvmOverloads constructor(
    var productName: String = "",
    var price: String = "0",
    var selerName: String = "",
    var productPhoto: String = "",
    var discount: String = "",
    var type:String=""

)