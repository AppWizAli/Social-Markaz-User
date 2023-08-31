package com.socialmarkaz.app.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.socialmarkaz.app.R
import com.socialmarkaz.app.databinding.ActivityProductDetailsBinding

class ActivityProductDetails : AppCompatActivity() {
    private lateinit var binding:ActivityProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProductDetailsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.BuyNow.setOnClickListener {
            startActivity(Intent(this,ActivityBuy::class.java))
        }
    }
}