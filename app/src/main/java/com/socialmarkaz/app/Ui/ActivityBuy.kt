package com.socialmarkaz.app.Ui

import User
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.enfotrix.lifechanger.Utils
import com.socialmarkaz.app.Constants
import com.socialmarkaz.app.Models.ModelPaymentDetails
import com.socialmarkaz.app.Models.Product
import com.socialmarkaz.app.Models.ProductViewModel
import com.socialmarkaz.app.R
import com.socialmarkaz.app.SharedPrefManager
import com.socialmarkaz.app.databinding.ActivityBuyBinding
import kotlinx.coroutines.launch

class ActivityBuy : AppCompatActivity() {
    private lateinit var binidng: ActivityBuyBinding
    private lateinit var user: User
    private lateinit var sharedPrefManager: SharedPrefManager
    private lateinit var recievedProduct: Product


    private lateinit var utils: Utils
    private lateinit var constants: Constants
    private lateinit var mContext: Context


    private val productViewModel: ProductViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binidng = ActivityBuyBinding.inflate(layoutInflater)
        setContentView(binidng.root)
        mContext = this@ActivityBuy
        utils = Utils(mContext)
        constants = Constants()
        sharedPrefManager = SharedPrefManager(mContext)

        recievedProduct = intent.getSerializableExtra("product") as Product
        user = User()
        binidng.btnPayment.setOnClickListener {
            showPaymentDialog()
        }

        binidng.tvBalance.text = recievedProduct.price


        user = sharedPrefManager.getUser()
        binidng.tvAccountHolderName.text = user.firstName
        binidng.tvAccountNumber.text = sharedPrefManager.getAccountNumber()


    }

    private fun showPaymentDialog() {
        var dialog = Dialog(this@ActivityBuy)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_confirm_order)
        var btnyes = dialog.findViewById<Button>(R.id.btn_yes)

        dialog.show()
        btnyes.setOnClickListener {
            recievedProduct.cartType = constants.PRODUCT_TYPE_PURCH
            lifecycleScope.launch {
                productViewModel.updateProduct(recievedProduct).observe(this@ActivityBuy)
                {
                    if (it == true) {
                        Toast.makeText(
                            this@ActivityBuy,
                            "Product will deleived to you within few days",
                            Toast.LENGTH_SHORT
                        ).show()

                        dialog.dismiss()
                    } else
                        Toast.makeText(
                            this@ActivityBuy,
                            constants.SOMETHING_WENT_WRONG_MESSAGE,
                            Toast.LENGTH_SHORT
                        ).show()
                    dialog.dismiss()
                }
            }


        }



}
}