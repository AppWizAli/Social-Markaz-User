package com.socialmarkaz.app.Ui

import User
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.enfotrix.lifechanger.Utils
import com.google.firebase.Timestamp
import com.socialmarkaz.app.Constants
import com.socialmarkaz.app.Data.Repo
import com.socialmarkaz.app.Models.UserViewModel
import com.socialmarkaz.app.R
import com.socialmarkaz.app.databinding.ActivityLoginBinding
import com.socialmarkaz.app.databinding.ActivitySignUpBinding
import kotlinx.coroutines.launch

class ActivitySignUp : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()
    private lateinit var utils: Utils
    private lateinit var mContext: Context
    private lateinit var constants: Constants

    private lateinit var repo: Repo
    private lateinit var user: User


    private lateinit var binding: ActivitySignUpBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Signin.setOnClickListener {

                startActivity(Intent(mContext, ActivityLogin::class.java))
            }

            binding.Signup.setOnClickListener {
                if (!(binding.checkTerms.isChecked))
                    Toast.makeText(mContext, "Please confirm privacy and terms conditions", Toast.LENGTH_SHORT).show()
               else if ((!IsEmpty()) && IsValid()) {
                    saveuser()
                }

            }

    }

    private fun saveuser() {
         user=User(
            binding.tilMail.editText?.text.toString(),
            binding.tilName.editText?.text.toString(),
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            binding.tilPass.editText?.text.toString(),
            "",
            "",
            Timestamp.now()
        )
        utils.startLoadingAnimation()
        lifecycleScope.launch {
            userViewModel.addUser(user).observe(this@ActivitySignUp) {
                utils.endLoadingAnimation()
                if (it == true) {
                    Toast.makeText(mContext, constants.USER_SIGNUP_MESSAGE, Toast.LENGTH_SHORT).show()


                    startActivity(Intent(mContext,ActivityLogin::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
                    finish()
                }
                else Toast.makeText(mContext, constants.USER_SIGNUP_FAILURE_MESSAGE, Toast.LENGTH_SHORT).show()

            }
        }
    }





    private fun IsValid(): Boolean {
        if (binding.tilPass.editText?.text.toString()
                .equals(binding.tilPassc.editText?.toString())
        ) return true
        else {
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
            return false
        }

    }

    private fun IsEmpty(): Boolean {

        val result = MutableLiveData<Boolean>()
        result.value = true
        if (binding.tilName.editText?.text.toString().isEmpty()) binding.tilName.editText?.error =
            "Empty Name"
        else if (binding.tilMail.editText?.text.toString()
                .isEmpty()
        ) binding.tilMail.editText?.error = "Empty Email"
        else if (binding.tilPass.editText?.text.toString()
                .isEmpty()
        ) binding.tilPass.editText?.error = "Empty Password"
        else if (binding.tilPassc.editText?.text.toString()
                .isEmpty()
        ) binding.tilPassc.editText?.error = "Empty Confirm Password"
        //else if (binding.etMobileNumber.editText?.text.toString().isEmpty()) binding.etMobileNumber.editText?.error = "Empty Phone"
        else result.value = false
        return result.value!!
    }
}