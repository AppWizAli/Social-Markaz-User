package com.socialmarkaz.app.Ui

import User
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.Timestamp
import com.socialmarkaz.app.Constants
import com.socialmarkaz.app.Models.ModelPaymentDetails
import com.socialmarkaz.app.Models.UserViewModel
import com.socialmarkaz.app.Models.Utils
import com.socialmarkaz.app.R
import com.socialmarkaz.app.SharedPrefManager
import com.socialmarkaz.app.databinding.ActivityUserDetailsBinding
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class ActivityUserDetails : AppCompatActivity() {


    private val IMAGE_PICKER_REQUEST_CODE = 200

    private var imageURI: Uri? = null

    private var UserProfilePhoto: Boolean = false

    private val userViewModel: UserViewModel by viewModels()

    private lateinit var binding: ActivityUserDetailsBinding

    private lateinit var imgProfilePhoto: ImageView


    private lateinit var utils: Utils
    private lateinit var mContext: Context
    private lateinit var constants: Constants
    private lateinit var user: User
    private lateinit var sharedPrefManager: SharedPrefManager
    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mContext = this@ActivityUserDetails
        utils = Utils(mContext)
        constants = Constants()
        sharedPrefManager = SharedPrefManager(mContext)
        user = User()
        binding.btnStart.visibility = View.GONE
        checkData()
        binding.layUserPhone.setOnClickListener {

            if (sharedPrefManager.isPhoneNumberAdded()) Toast.makeText(
                mContext,
                "Phone already added",
                Toast.LENGTH_SHORT
            ).show()
            else startActivity(
                Intent(
                    mContext,
                    ActivityPhoneNumber::class.java
                ).putExtra(constants.KEY_ACTIVITY_FLOW, constants.VALUE_ACTIVITY_FLOW_USER_DETAILS)
            )
            checkData()
        }

        binding.layUserPayment.setOnClickListener {

            if (sharedPrefManager.isPaymentAdded()) Toast.makeText(
                mContext,
                "User Payment Details already added!",
                Toast.LENGTH_SHORT
            ).show()
            else showPaymenttDialog(constants.VALUE_DIALOG_FLOW_USER_ACCOUNT)

        }

        binding.layUserProfilePhoto.setOnClickListener {
            if (sharedPrefManager.isUserPhotoAdded()) Toast.makeText(
                mContext,
                "User photo already added!",
                Toast.LENGTH_SHORT
            ).show()
            else showPhotoDialog()
        }
        binding.layUserGender.setOnClickListener {
            if (sharedPrefManager.isGenderAdded()) Toast.makeText(
                mContext,
                "User Gender already added!",
                Toast.LENGTH_SHORT
            ).show()
            else showAddGenderDialog(constants.VALUE_ACTIVITY_FLOW_USER_DETAILS)
        }
        binding.layUserLocation.setOnClickListener {


            if (sharedPrefManager.isLocationAdded()) Toast.makeText(
                mContext,
                "Location already added!",
                Toast.LENGTH_SHORT
            ).show()
            else showLocationDialog(constants.VALUE_ACTIVITY_FLOW_USER_DETAILS)

        }
        binding.btnStart.setOnClickListener {
            startApp()

        }

    }

    @SuppressLint("ResourceAsColor")
    private fun checkData() {
        var checkCounter: Int = 0
        if (sharedPrefManager.isPhoneNumberAdded()) {
            checkCounter++
            binding.tvHeaderUserPhoneNumber.text = "Completed"
            binding.tvHeaderUserPhoneNumber.setTextColor(Color.parseColor("#2F9B47"))
            binding.imgCheckUserPhoneNumber.setImageResource(R.drawable.check_small)
        }
        if (sharedPrefManager.isPaymentAdded()) {
            checkCounter++
            binding.tvHeaderUserPayment.text = "Completed"
            binding.tvHeaderUserPayment.setTextColor(Color.parseColor("#2F9B47"))
            binding.imgCheckUserPayment.setImageResource(R.drawable.check_small)
        }

        if (sharedPrefManager.isUserPhotoAdded()) {
            checkCounter++
            binding.tvHeaderUserPhoto.text = "Completed"
            binding.tvHeaderUserPhoto.setTextColor(Color.parseColor("#2F9B47"))
            binding.imgCheckUserPhoto.setImageResource(R.drawable.check_small)
        }


        if (sharedPrefManager.isGenderAdded()) {
            checkCounter++
            binding.tvHeaderUserGender.text = "Completed"
            binding.tvHeaderUserGender.setTextColor(Color.parseColor("#2F9B47"))
            binding.imgCheckUserGender.setImageResource(R.drawable.check_small)
        }

        if (sharedPrefManager.isLocationAdded()) {
            checkCounter++
            binding.tvHeaderUserLocation.text = "Completed"
            binding.tvHeaderUserLocation.setTextColor(Color.parseColor("#2F9B47"))
            binding.imgCheckUserLocation.setImageResource(R.drawable.check_small)
        }


        if (checkCounter == 5) {
            binding.btnStart.visibility = View.VISIBLE

        }
    }

    fun showPaymenttDialog(type: String) {

        dialog = Dialog(mContext)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_add_account)

        val saccount = dialog.findViewById<Spinner>(R.id.saccount)
        val etAccountHolderName = dialog.findViewById<EditText>(R.id.etAccountHolderName)
        val etAccountNumber = dialog.findViewById<EditText>(R.id.etAccountNumber)
        val btnAddAccount = dialog.findViewById<Button>(R.id.btnAddAccount)
        btnAddAccount.setOnClickListener {

            addUserPaymentDetails(
                "",
                saccount.selectedItem.toString(),
                etAccountHolderName.text.toString(),
                etAccountNumber.text.toString()
            )
        }

        dialog.show()
    }


    fun addUserPaymentDetails(
        id: String,
        accounttitle: String,
        accholdername: String,
        accountNumber: String
    ) {


        utils.startLoadingAnimation()
        lifecycleScope.launch {
            userViewModel.addUserAccount(
                ModelPaymentDetails(
                    id,
                    accounttitle,
                    accountNumber,
                    accholdername
                )
            )
                .observe(this@ActivityUserDetails) {
                    utils.endLoadingAnimation()
                    if (it == true) {
                        sharedPrefManager.setAccountNumber(accountNumber)
                        sharedPrefManager.putUserAccount(true)
                        Toast.makeText(
                            mContext,
                            constants.ACCOPUNT_ADDED_MESSAGE,
                            Toast.LENGTH_SHORT
                        ).show()
                        dialog.dismiss()
                        checkData()
                    } else {
                        Toast.makeText(
                            mContext,
                            constants.SOMETHING_WENT_WRONG_MESSAGE,
                            Toast.LENGTH_SHORT
                        ).show()
                        dialog.dismiss()
                    }

                }
        }

    }


    fun showPhotoDialog() {
        UserProfilePhoto = false

        dialog = Dialog(mContext)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_profile_photo_upload)

        imgProfilePhoto = dialog.findViewById<ImageView>(R.id.imgProfilePhoto)
        val tvSelect = dialog.findViewById<TextView>(R.id.tvSelect)
        val btnUplodProfile = dialog.findViewById<Button>(R.id.btnUplodProfile)

        tvSelect.setOnClickListener {
            UserProfilePhoto = true
            val pickImage = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(pickImage, IMAGE_PICKER_REQUEST_CODE)
        }

        btnUplodProfile.setOnClickListener {

            lifecycleScope.launch {
                if (imageURI != null) addUserPhoto(imageURI!!, "UserProfilePhoto")
                else Toast.makeText(mContext, "Please Select Image", Toast.LENGTH_SHORT).show()
            }


        }

        dialog.show()
    }

    suspend fun addUserPhoto(imageUri: Uri, type: String) {
        utils.startLoadingAnimation()
        userViewModel.uploadPhoto(imageUri, type)
            .addOnSuccessListener { taskSnapshot ->
                taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
                    var user: User = sharedPrefManager.getUser()
                    user.photo = uri.toString()

                    lifecycleScope.launch {

                        userViewModel.updateUser(user).observe(this@ActivityUserDetails) {
                            utils.endLoadingAnimation()
                            if (it == true) {
                                sharedPrefManager.saveUser(user)
                                sharedPrefManager.putUserPhoto(true)
                                Toast.makeText(
                                    mContext,
                                    "Profile Photo Updated",
                                    Toast.LENGTH_SHORT
                                ).show()
                                dialog.dismiss()
                                checkData()
                            } else Toast.makeText(
                                mContext,
                                constants.SOMETHING_WENT_WRONG_MESSAGE,
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }

                }
                    .addOnFailureListener { exception ->
                        Toast.makeText(mContext, exception.message + "", Toast.LENGTH_SHORT).show()
                    }
            }
            .addOnFailureListener {
                Toast.makeText(mContext, "Failed to upload profile pic", Toast.LENGTH_SHORT).show()
            }
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_PICKER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            when {
                UserProfilePhoto -> {
                    Glide.with(mContext).load(data?.data).into(imgProfilePhoto)
                    imageURI = data?.data
                }
            }


        }
    }

    fun showAddGenderDialog(type: String) {
        dialog = Dialog(mContext)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog.setContentView(R.layout.dialog_gender_add)

        val Male = dialog.findViewById<RadioButton>(R.id.radioMale)
        val Female = dialog.findViewById<RadioButton>(R.id.radioFemale)
        val Other = dialog.findViewById<RadioButton>(R.id.radioOther)
        val select = dialog.findViewById<Button>(R.id.btngenderok)
        select.setOnClickListener {
            val selectedGender = when {
                Male.isChecked -> "Male"
                Female.isChecked -> "Female"
                Other.isChecked -> "Other"
                else -> ""

            }

            addgender(selectedGender)

        }
        dialog.show()
    }

    private fun addgender(selectedGender: String) {

        user = User(
            sharedPrefManager.getUser().email,
            sharedPrefManager.getUser().firstName,
            selectedGender,
            "",
            sharedPrefManager.getUser().phone,
            "",
            sharedPrefManager.getUser().photo,
            "",
            "",
            sharedPrefManager.getUser().pin,
            "",
            "",
            sharedPrefManager.getUser().createdAt
        )
        utils.startLoadingAnimation()
        lifecycleScope.launch {
            userViewModel.updateUser(user)
                .observe(this@ActivityUserDetails) {
                    utils.endLoadingAnimation()
                    if (it == true) {
                        sharedPrefManager.PutUserGender(true)
                        Toast.makeText(
                            mContext,
                            constants.USER_GENDER_ADDED_MESSAGE,
                            Toast.LENGTH_SHORT
                        ).show()
                        dialog.dismiss()
                    } else {
                        Toast.makeText(
                            mContext,
                            constants.SOMETHING_WENT_WRONG_MESSAGE,
                            Toast.LENGTH_SHORT
                        ).show()
                        dialog.dismiss()
                    }

                }
        }
    }

    private fun showLocationDialog(type: String) {
        var dialog = Dialog(mContext)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog.setContentView(R.layout.dialog_add_location)
        var location = dialog.findViewById<EditText>(R.id.etUserLocation)
        var add = dialog.findViewById<Button>(R.id.btnAddLocation)
        add.setOnClickListener {
            addUserLocation(location.text.toString())
        }
        dialog.show()

    }

    private fun addUserLocation(location: String) {


        user = User(
            sharedPrefManager.getUser().email,
            sharedPrefManager.getUser().firstName,
            sharedPrefManager.getUser().gender,
            location,
            sharedPrefManager.getUser().phone,
            "",
            sharedPrefManager.getUser().photo,
            "",
            "",
            sharedPrefManager.getUser().pin,
            "",
            "",
            sharedPrefManager.getUser().createdAt
        )
        utils.startLoadingAnimation()
        lifecycleScope.launch {
            userViewModel.updateUser(user)
                .observe(this@ActivityUserDetails) {
                    utils.endLoadingAnimation()
                    if (it == true) {
                        sharedPrefManager.PutUserLocation(true)
                        Toast.makeText(
                            mContext,
                            constants.USER_LOCATION_ADDED_MESSAGE,
                            Toast.LENGTH_SHORT
                        ).show()
                        dialog.dismiss()
                    } else {
                        Toast.makeText(
                            mContext,
                            constants.SOMETHING_WENT_WRONG_MESSAGE,
                            Toast.LENGTH_SHORT
                        ).show()
                        dialog.dismiss()
                    }

                }
        }

    }


    fun startApp() {
        var user: User = sharedPrefManager.getUser()
        utils.startLoadingAnimation()
        sharedPrefManager.saveUser(user)
        sharedPrefManager.setLogin(isLoggedIn = true)
        Toast.makeText(
            mContext,
            "Profile Completed Successfully!",
            Toast.LENGTH_SHORT
        ).show()
        startActivity(
            Intent(
                mContext,
                MainActivity::class.java
            ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        )
        finish()
    }

}





