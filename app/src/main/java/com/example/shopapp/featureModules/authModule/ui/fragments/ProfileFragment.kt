package com.example.shopapp.featureModules.authModule.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.shopapp.R
import com.example.shopapp.application.ShopApplication
import com.example.shopapp.databinding.FragmentProfileBinding
import com.example.shopapp.featureModules.authModule.di.DaggerAuthComponent
import com.example.shopapp.featureModules.authModule.models.UserModel
import com.example.shopapp.featureModules.authModule.ui.activities.LoginActivity
import com.example.shopapp.featureModules.orderModule.ui.activities.StatsActivity
import com.example.shopapp.featureModules.productModule.ui.activities.AddProductActivity
import com.example.shopapp.utility.DataStoreManager
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import javax.inject.Inject


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    @Inject
    lateinit var dataStoreManager: DataStoreManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile, container, false)
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerAuthComponent.builder().appComponent((requireActivity().application as ShopApplication).applicationComponent()).build().also {
            it.inject(this)
        }

        with(binding){

            val user = runBlocking { dataStoreManager.user.first() }

            username.text = user.userName
            email.text = user.email

            if (user.isAdmin!!){
                addproductCardview.visibility = View.VISIBLE
                statsCardview.visibility = View.VISIBLE
            }
            else{
                addproductCardview.visibility = View.GONE
                statsCardview.visibility = View.GONE
            }

            addproductCardview.setOnClickListener {
                startActivity(Intent(requireActivity(),AddProductActivity::class.java))
            }

            statsCardview.setOnClickListener {
                startActivity(Intent(requireActivity(),StatsActivity::class.java))
            }



            logoutCardview.setOnClickListener {
                lifecycleScope.launchWhenStarted{
                   val user = UserModel(userName = "", email = "", accessToken = "", isAdmin = false )
                    dataStoreManager.saveUser(user)
                    requireActivity().finish()
                    startActivity(Intent(requireActivity(),LoginActivity::class.java))

                }


            }
        }
    }


}