package com.socialmarkaz.app.Models

import android.app.Application
import android.content.Context
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


    fun getRecProductAdapter(listener:Context,itemClickListener: ProductAdapter.OnItemClickListener): ProductAdapter {
        return ProductAdapter(
            sharedPrefManager.getRecProductList()
                .filter { it.type.equals(constants.PRODUCT_TYPE_REC) },listener,itemClickListener)
    }

    fun getFollProductAdapter(listener:Context,itemClickListener: ProductAdapter.OnItemClickListener): ProductAdapter {
        return ProductAdapter(
            sharedPrefManager.getFollProductList()
                .filter { it.type.equals(constants.PRODUCT_TYPE_FOLL) },listener,itemClickListener)
    }

    fun getPurchProductAdapter(listener:Context,itemClickListener: ProductAdapter.OnItemClickListener): ProductAdapter {

        return ProductAdapter(
            sharedPrefManager.getPurchProductList()
                .filter { it.type.equals(constants.PRODUCT_TYPE_PURCH) },listener,itemClickListener)
    }

    fun getCartProductAdapter(listener:Context,itemClickListener: ProductAdapter.OnItemClickListener): ProductAdapter {

        return ProductAdapter(
            sharedPrefManager.getCartProductList()
                .filter { it.type.equals(constants.PRODUCT_TYPE_CART) },listener,itemClickListener)
    }

    suspend fun getProducts(): Task<QuerySnapshot> {
        return userRepo.getproducts()
    }


}