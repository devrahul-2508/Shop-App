package com.example.shopapp.featureModules.authModule.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.shopapp.MainActivity
import com.example.shopapp.R
import com.example.shopapp.application.ShopApplication
import com.example.shopapp.databinding.ActivityLoginBinding
import com.example.shopapp.featureModules.authModule.di.DaggerAuthComponent
import com.example.shopapp.featureModules.authModule.models.UserModel
import com.example.shopapp.featureModules.authModule.viewmodels.AuthViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)

        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        DaggerAuthComponent.builder().appComponent((application as ShopApplication).applicationComponent()).build().also {
            it.inject(this)
            it.inject(authViewModel)
        }

        with(binding){
            btnLogin.setOnClickListener {
                loginUser()
            }
        }
    }
    private fun loginUser(){
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()

        val userModel = UserModel(email = email, password = password)

        authViewModel.loginUser(userModel).observe(this){
            if (it!=null){
                startActivity(Intent(this,MainActivity::class.java))
            }
            else{
                Toast.makeText(this,"Problem",Toast.LENGTH_SHORT).show()
            }
        }


    }
}