package com.example.shopapp.featureModules.authModule.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.shopapp.MainActivity
import com.example.shopapp.R
import com.example.shopapp.application.ShopApplication
import com.example.shopapp.databinding.ActivitySignUpBinding
import com.example.shopapp.featureModules.authModule.di.DaggerAuthComponent
import com.example.shopapp.featureModules.authModule.models.FcmTokenModel
import com.example.shopapp.featureModules.authModule.models.UserModel
import com.example.shopapp.featureModules.authModule.viewmodels.AuthViewModel
import com.example.shopapp.utility.DataStoreManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    lateinit var authViewModel: AuthViewModel
    private var accessToken: String ?=null
    private val TAG = "1234"
    private var token = ""

    @Inject
    lateinit var dataStoreManager: DataStoreManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)

        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        DaggerAuthComponent.builder().appComponent((application as ShopApplication).applicationComponent()).build().also {
            it.inject(this)
            it.inject(authViewModel)
        }
        with(binding){
            btnSignUp.setOnClickListener {
                signUpUser()
            }
        }


    }

    private fun signUpUser(){
        with(binding){
            val userName = username.text.toString()
            val email = email.text.toString()
            val password = password.text.toString()





                val userModel = UserModel(
                    userName = userName,
                    email = email,
                    password = password
                )

                authViewModel.registerUser(userModel).observe(this@SignUpActivity){response->
                    if (response.success){

                        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                            if (!task.isSuccessful) {
                                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                                return@OnCompleteListener
                            }

                            // Get new FCM registration token
                            val fcmToken = task.result

                            val fcmTokenModel = FcmTokenModel(
                                userId = response.response?.id,
                                fcmToken = fcmToken
                            )

                            authViewModel.setFcmToken(fcmTokenModel).observe(this@SignUpActivity){
                                if (it.success){
                                    lifecycleScope.launchWhenStarted{
                                        dataStoreManager.saveUser(response.response!!)
                                    }
                                    startActivity(Intent(this@SignUpActivity,MainActivity::class.java))
                                    finish()
                                }

                            }
                        })



                    }
                    else{
                        Toast.makeText(this@SignUpActivity,response.message, Toast.LENGTH_SHORT).show()
                    }
                }







        }






    }
}