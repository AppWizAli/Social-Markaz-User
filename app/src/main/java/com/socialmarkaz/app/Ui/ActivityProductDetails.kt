package com.socialmarkaz.app.Ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.enfotrix.lifechanger.Utils
import com.socialmarkaz.app.Constants
import com.socialmarkaz.app.Models.Product
import com.socialmarkaz.app.Models.ProductViewModel
import com.socialmarkaz.app.R
import com.socialmarkaz.app.SharedPrefManager
import com.socialmarkaz.app.databinding.ActivityLoginBinding
import com.socialmarkaz.app.databinding.ActivityProductDetailsBinding
import com.socialmarkaz.app.databinding.FragmentSearchBinding

class ActivityProductDetails : AppCompatActivity() {
    private lateinit var utils: Utils
    private lateinit var constants: Constants
    private lateinit var mContext: Context
    private lateinit var sharedPrefManager: SharedPrefManager


    private val productViewModel: ProductViewModel by viewModels()


    private lateinit var binding : ActivityProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mContext=this@ActivityProductDetails
        utils = Utils(mContext)
        constants= Constants()
        sharedPrefManager = SharedPrefManager(mContext)
        val receivedProduct = intent.getSerializableExtra("product") as Product
        binding.productName.text=receivedProduct.productName
        binding.category.text=receivedProduct.catagory
        binding.btnPrice.text=receivedProduct.price
        Glide.with(this)
            .load(receivedProduct.productPhoto)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.photo)





    }
}