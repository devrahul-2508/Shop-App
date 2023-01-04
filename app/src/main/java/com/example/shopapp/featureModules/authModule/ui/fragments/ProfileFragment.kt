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
import com.example.shopapp.featureModules.authModule.ui.activities.LoginActivity
import com.example.shopapp.utility.DataStoreManager
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
            logoutCardview.setOnClickListener {
                GlobalScope.launch (Dispatchers.Main){
                    dataStoreManager.saveAccessToken("")
                    requireActivity().finish()
                    startActivity(Intent(requireActivity(),LoginActivity::class.java))

                }


            }
        }
    }


}