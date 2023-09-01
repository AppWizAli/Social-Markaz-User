package com.socialmarkaz.app.Models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.socialmarkaz.app.Adapters.ProductAdapter
import com.socialmarkaz.app.Constants
import com.socialmarkaz.app.Data.Repo
import com.socialmarkaz.app.SharedPrefManager

class ProductViewModel(context: Application) : AndroidViewModel(context) {
    private val db = Firebase.firestore
    private val userRepo = Repo(context)
    private val sharedPrefManager = SharedPrefManager(context)

    private var constants = Constants()


    fun getRecProductAdapter(): ProductAdapter {
        return ProductAdapter(
            sharedPrefManager.getRecProductList()
                .filter { it.type.equals(constants.PRODUCT_TYPE_REC) })
    }

    fun getFollProductAdapter(): ProductAdapter {
        return ProductAdapter(
            sharedPrefManager.getFollProductList()
                .filter { it.type.equals(constants.PRODUCT_TYPE_FOLL) })
    }

    fun getPurchProductAdapter(): ProductAdapter {

        return ProductAdapter(
            sharedPrefManager.getPurchProductList()
                .filter { it.type.equals(constants.PRODUCT_TYPE_PURCH) })
    }

    fun getCartProductAdapter(): ProductAdapter {

        return ProductAdapter(
            sharedPrefManager.getCartProductList()
                .filter { it.type.equals(constants.PRODUCT_TYPE_CART) })
    }

    suspend fun getProducts(): Task<QuerySnapshot> {
        return userRepo.getproducts()
    }


}