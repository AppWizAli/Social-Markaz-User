package com.socialmarkaz.app.Data

import User
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import com.socialmarkaz.app.Constants
import com.socialmarkaz.app.Models.ModelPaymentDetails
import com.socialmarkaz.app.SharedPrefManager
class Repo(val context: Context) {






    private var constants= Constants()
    private val sharedPrefManager = SharedPrefManager(context)
    private val token = sharedPrefManager.getToken()



    ///////////////////////////   FIREBASE    //////////////////////////////////
    private val db = Firebase.firestore
    private val firebaseStorage = Firebase.storage
    private val storageRef = firebaseStorage.reference

    private val AccountsCollection = db.collection(constants.ACCOUNTS_COLLECTION)
    private val productCollection = db.collection(constants.PRODUCT_COLLECTION)
    private val NotificationCollection = db.collection(constants.NOTIFICATION_COLLECTION)
    private val UserCollection = db.collection(constants.USER_COLLECTION)


    suspend fun registerUser(user: User): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        UserCollection.add(user).addOnSuccessListener { documents ->
            result.value =true
        }.addOnFailureListener {
            result.value = false
        }
        return result
    }


    suspend fun isUserExist(EMAIL: String): Task<QuerySnapshot> {
        return UserCollection.whereEqualTo(constants.USER_EMAIL, EMAIL).get()
    }

    suspend fun uploadPhoto(imageUri: Uri, type:String): UploadTask {
        return storageRef.child(    type+"/"+sharedPrefManager.getToken()).putFile(imageUri)
    }


    suspend fun uploadPhotoRefrence(imageUri:Uri,type:String): StorageReference {

        return storageRef.child(    type+"/"+sharedPrefManager.getToken())
    }
    suspend fun updateUser(user: User): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        UserCollection.document(sharedPrefManager.getToken()).set(user).addOnSuccessListener { documents ->
            result.value =true
        }.addOnFailureListener {
            result.value = false
        }
        return result
    }
/*    suspend fun updateUser(user: User): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        UserCollection.document(user.user_id).set(user).addOnSuccessListener { documents ->
            result.value =true
        }.addOnFailureListener {
            result.value = false
        }
        return result
    }*/
    suspend fun userAccounts(token: String ): Task<QuerySnapshot> {
        return AccountsCollection.whereEqualTo(constants.ACCOUNT_HOLDER, token).get()
    }
    suspend fun getUser( docID: String): Task<DocumentSnapshot> {
        return UserCollection.document(docID).get()
        // return NomineesCollection.whereEqualTo("nominator", nominator).get()
    }

    suspend fun registerPaymentDetails(modelPaymentDetails:ModelPaymentDetails): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        modelPaymentDetails.account_holder=sharedPrefManager.getToken()
        var documentRef= AccountsCollection.document()
        modelPaymentDetails.docId=documentRef.id

        documentRef.set(modelPaymentDetails).addOnSuccessListener { documents ->
            result.value =true
        }.addOnFailureListener {
            result.value = false
        }
        return result
    }


    suspend fun getproducts(): Task<QuerySnapshot> {
        return productCollection.get()
    }

}