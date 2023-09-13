package com.socialmarkaz.app.Ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.enfotrix.lifechanger.Utils
import com.socialmarkaz.app.Constants
import com.socialmarkaz.app.Fragments.FragmentCart
import com.socialmarkaz.app.Models.ModelPaymentDetails
import com.socialmarkaz.app.Models.Product
import com.socialmarkaz.app.Models.ProductViewModel
import com.socialmarkaz.app.Models.Seller
import com.socialmarkaz.app.R
import com.socialmarkaz.app.SharedPrefManager
import com.socialmarkaz.app.databinding.ActivityLoginBinding
import com.socialmarkaz.app.databinding.ActivityProductDetailsBinding
import com.socialmarkaz.app.databinding.FragmentSearchBinding
import kotlinx.coroutines.launch

class ActivityProductDetails : AppCompatActivity() {
    private lateinit var utils: Utils
    private lateinit var constants: Constants
    private lateinit var mContext: Context
    private lateinit var sharedPrefManager: SharedPrefManager


    private val productViewModel: ProductViewModel by viewModels()


    private lateinit var binding: ActivityProductDetailsBinding
    private lateinit var seller: Seller
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mContext = this@ActivityProductDetails
        utils = Utils(mContext)
        constants = Constants()
        sharedPrefManager = SharedPrefManager(mContext)
        seller = Seller()
        val receivedProduct = intent.getSerializableExtra("product") as Product

        binding.btnBuyNow.setOnClickListener {
            startActivity(
                Intent(mContext, ActivityBuy::class.java).putExtra(
                    "product",
                    receivedProduct
                )
            )
        }
        binding.btnAddToCart.setOnClickListener {
            receivedProduct.cartType = constants.PRODUCT_TYPE_CART
            lifecycleScope.launch {
                productViewModel.updateProduct(receivedProduct).observe(this@ActivityProductDetails)
                {
                    if (it == true) {
                        Toast.makeText(
                            this@ActivityProductDetails,
                            "Add to cart Successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else
                        Toast.makeText(
                            this@ActivityProductDetails,
                            constants.SOMETHING_WENT_WRONG_MESSAGE,
                            Toast.LENGTH_SHORT
                        ).show()
                }
            }


        }









        lifecycleScope.launch {
            productViewModel.getSeller()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        if (task.result.size() > 0) {
                            for (document in task.result)
                                if (document.toObject(Seller::class.java).Seller_id.equals(
                                        receivedProduct.storeId
                                    )
                                ) {
                                    binding.storeName.text =
                                        document.toObject(Seller::class.java).store_name

                                }


                        }

                    } else Toast.makeText(
                        this@ActivityProductDetails,
                        "SOMETHING_WENT_WRONG_MESSAGE",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener {
                    utils.endLoadingAnimation()


                }

        }

        binding.productName.text = receivedProduct.productName
        binding.category.text = receivedProduct.catagory
        binding.btnPrice.text = receivedProduct.price
        Glide.with(this)
            .load(receivedProduct.productPhoto)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.photo)


    }
}