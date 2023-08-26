package com.socialmarkaz.app.Ui

import User
import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.enfotrix.lifechanger.Utils
import com.socialmarkaz.app.Constants
import com.socialmarkaz.app.Models.UserViewModel
import com.socialmarkaz.app.R
import com.socialmarkaz.app.SharedPrefManager
import com.socialmarkaz.app.databinding.ActivityLoginBinding
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ActivityLogin : AppCompatActivity() {
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var binding : ActivityLoginBinding

    private lateinit var utils: Utils
    private lateinit var mContext: Context
    private var investorAccount: Boolean = true
    private lateinit var dialogPinUpdate: Dialog
    private lateinit var dialog1: Dialog

    private lateinit var constants: Constants
    private lateinit var user: User
    private lateinit var sharedPrefManager : SharedPrefManager
    private lateinit var dialog : Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mContext=this@ActivityLogin
        utils = Utils(mContext)
        constants= Constants()
        sharedPrefManager = SharedPrefManager(mContext)

        binding.CreateAccount.setOnClickListener {
            startActivity(Intent(mContext,ActivitySignUp::class.java))
        }


        binding.BtnLogin.setOnClickListener(View.OnClickListener {


            if((!IsEmpty()) )
                checkEMAIL(binding.EtUsername.editText?.text.toString())

        })
    }












    private fun checkEMAIL(email:String) {

        utils.startLoadingAnimation()
        lifecycleScope.launch{
            userViewModel.isUserExist(email)
                .addOnCompleteListener{task ->
                    utils.endLoadingAnimation()
                    if (task.isSuccessful) {

                        if(task.result.size()>0){
                            var token: String = ""
                            val documents = task.result
                            var user: User? = null
                            for (document in documents) {
                                user = document.toObject(User::class.java)
                                token= document.id
                            }
                           if(documents.size()==0)
                                binding.EtUsername.editText?.error =constants.USER_EMAIL_NOT_EXIST
                           else
                               user?.let { loginUser(it,binding.EtUsername.editText?.toString(),binding.EtPassword.editText?.toString(),token) }
                        }
                        else
                            binding.EtUsername.editText?.error =constants.USER_EMAIL_NOT_EXIST

                    }

                }
                .addOnFailureListener{
                    utils.endLoadingAnimation()
                    Toast.makeText(mContext, it.message+"", Toast.LENGTH_SHORT).show()
                }

        }
    }




















    private fun loginUser(user:User,email: String?, password: String?,token:String) {

        if (user != null) {
            if (user.email.equals(email)) {
                if (user.pin.equals(password)) {
                    if (user != null) userViewModel.saveLoginAuth(
                        user,
                        token,
                        true
                    )//usre +token+login_boolean
                    startActivity(
                        Intent(
                            mContext,
                            MainActivity::class.java
                        ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    )
                    finish()
                }
            else
                    Toast.makeText(mContext, "Incorrect Password", Toast.LENGTH_SHORT).show()/*
                else{
                    if(user!=null)userViewModel.saveLoginAuth(user, token, true)//usre +token+login_boolean
                    startActivity(Intent(mContext,ActivityUserDetails::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
                    finish()
                }


            }


        }
        else {
            Toast.makeText(mContext, "Incorrect Pin", Toast.LENGTH_SHORT).show()
        }*/

            }
            else
                Toast.makeText(mContext, "Incorrect email", Toast.LENGTH_SHORT).show()
        }
        else
            Toast.makeText(mContext, "Null user testing", Toast.LENGTH_SHORT).show()
    }


    private fun IsEmpty(): Boolean {

        val result = MutableLiveData<Boolean>()
        result.value = true
        if (binding.EtUsername.editText?.text.toString()
                .isEmpty()
        ) binding.EtUsername.editText?.error = "Empty Email"
        else if (binding.EtPassword.editText?.text.toString()
                .isEmpty()
        ) binding.EtPassword.editText?.error = "Empty Password"
       else result.value = false
        return result.value!!
    }

}