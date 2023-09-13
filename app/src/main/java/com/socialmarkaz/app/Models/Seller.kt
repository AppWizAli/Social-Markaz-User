package com.socialmarkaz.app.Models
import com.google.firebase.Timestamp
    data class Seller @JvmOverloads constructor(
        var email: String = "",
        var firstName: String = "",
        var location: String = "",
        var phone: String = "",
        var agreement: String = "",
        var photo: String = "",
        var cnic_front: String = "",
        var cnic_back: String = "",
        var pin: String = "",
        var Seller_id: String = "",
        var store_id: String = "",
        var store_icon: String = "",
        var store_name: String = "",
        var store_description: String = "",
        var store_link: String = "",
        var payment_detail: String = "",
        var status:String="",
        var store_location:String="",
         val createdAt: Timestamp = Timestamp.now() // Creation timestamp
)