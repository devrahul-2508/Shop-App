package com.example.shopapp.featureModules.authModule.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.shopapp.MainActivity
import com.example.shopapp.R
import com.example.shopapp.application.ShopApplication
import com.example.shopapp.databinding.ActivityLoginBinding
import com.example.shopapp.featureModules.authModule.di.DaggerAuthComponent
import com.example.shopapp.featureModules.authModule.models.FcmTokenModel
import com.example.shopapp.featureModules.authModule.models.UserModel
import com.example.shopapp.featureModules.authModule.viewmodels.AuthViewModel
import com.example.shopapp.utility.DataStoreManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel
    private var accessToken: String ?=null
    private var user:UserModel?=null
    private val TAG = "1234"

    @Inject
    lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)

        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        DaggerAuthComponent.builder().appComponent((application as ShopApplication).applicationComponent()).build().also {
            it.inject(this)
            it.inject(authViewModel)
        }

        handleIntent()


        with(binding){
            btnLogin.setOnClickListener {
                loginUser()
            }
            btnSignUpSuggestion.setOnClickListener {
                startActivity(Intent(this@LoginActivity,SignUpActivity::class.java))

            }
        }
    }
    private fun loginUser(){
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()

        val userModel = UserModel(email = email, password = password)

        authViewModel.loginUser(userModel).observe(this){ response->

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

                    authViewModel.setFcmToken(fcmTokenModel).observe(this){
                        if (it.success){
                            lifecycleScope.launchWhenStarted{
                                dataStoreManager.saveUser(response.response!!)
                        }
                            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                            finish()
                    }

                }

                })



            }
            else{
                Toast.makeText(this,response.message,Toast.LENGTH_SHORT).show()
            }
        }


    }
    private fun handleIntent(){



        user = runBlocking { dataStoreManager.user.first() }
        Log.d("BAMUSER",user.toString())

        if (user?.accessToken!!.isNotEmpty() || user?.accessToken!!.isNotBlank()){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }




    }
}