package com.socialmarkaz.app.Models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.socialmarkaz.app.Adapters.RecomendedProductAdapter
import com.socialmarkaz.app.Constants
import com.socialmarkaz.app.Data.Repo
import com.socialmarkaz.app.SharedPrefManager

class ProductViewModel(context: Application) : AndroidViewModel(context) {
    private val db = Firebase.firestore
    private val userRepo = Repo(context)
    private val sharedPrefManager = SharedPrefManager(context)

    private var constants= Constants()









    fun getRecProductAdapter(): RecomendedProductAdapter {
        return RecomendedProductAdapter(sharedPrefManager.getRecProductList())
    }


    suspend fun getProducts(): Task<QuerySnapshot> {
        return userRepo.getproducts()
    }


}