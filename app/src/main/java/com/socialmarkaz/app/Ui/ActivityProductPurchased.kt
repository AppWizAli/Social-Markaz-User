package com.socialmarkaz.app.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.socialmarkaz.app.Adapters.ProductAdapter
import com.socialmarkaz.app.Models.Product
import com.socialmarkaz.app.Models.ProductViewModel
import com.socialmarkaz.app.R
import com.socialmarkaz.app.SharedPrefManager
import com.socialmarkaz.app.databinding.ActivityProductPurchasedBinding

class ActivityProductPurchased : AppCompatActivity() ,ProductAdapter.OnItemClickListener{
    private  lateinit var binding:ActivityProductPurchasedBinding
    private  lateinit var sharedPrefManager: SharedPrefManager

     private val productViewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityProductPurchasedBinding.inflate(layoutInflater)
        setContentView(binding.root)

sharedPrefManager= SharedPrefManager((this@ActivityProductPurchased))
        binding.rvproductpurchased.layoutManager=LinearLayoutManager(this@ActivityProductPurchased)
        binding.rvproductpurchased.adapter=productViewModel.getPurchProductAdapter(this@ActivityProductPurchased,this)


    }

    override fun onItemClick(product: Product) {

    }

    override fun onDeleteClick(product: Product) {

    }
}