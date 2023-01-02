package com.example.shopapp.featureModules.mainModule.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.example.shopapp.R
import com.example.shopapp.application.ShopApplication
import com.example.shopapp.databinding.FragmentHomeBinding
import com.example.shopapp.featureModules.mainModule.di.DaggerMainComponent
import com.example.shopapp.featureModules.mainModule.ui.adapters.MainHomeAdapter
import com.example.shopapp.featureModules.mainModule.viewModels.MainViewModel
import com.example.shopapp.featureModules.productModule.di.DaggerProductComponent
import com.example.shopapp.featureModules.productModule.ui.activities.SearchActivity
import com.example.shopapp.featureModules.productModule.ui.adapters.ProductsPagingAdapter
import com.example.shopapp.featureModules.productModule.viewModels.ProductViewModel


class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    private lateinit var productViewModel: ProductViewModel
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: ProductsPagingAdapter
    private lateinit var mainHomeAdapter: MainHomeAdapter
    private var title: String?=null
    private var category: String?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productViewModel = ViewModelProvider(requireActivity())[ProductViewModel::class.java]
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        DaggerProductComponent.builder().appComponent((requireActivity().application as ShopApplication).applicationComponent()).build().also {
            it.inject(this)
            it.inject(productViewModel)
        }
        DaggerMainComponent.builder().appComponent((requireActivity().application as ShopApplication).applicationComponent()).build().also {
            it.inject(this)
            it.inject(mainViewModel)
        }
        adapter = ProductsPagingAdapter(requireContext())
        binding.productRecycler.layoutManager = GridLayoutManager(requireContext(),2,VERTICAL,false)
        binding.productRecycler.adapter = adapter

        //getProducts()
        getMainModels()


        binding.searchView.setOnClickListener {
            startActivity(Intent(requireActivity(),SearchActivity::class.java))
        }
    }

 /* private fun getProducts(){

      productViewModel.getAllProducts(title,category).observe(requireActivity()){
          lifecycleScope.launchWhenStarted {
              adapter.submitData(it)
          }
      }
  }*/

    private fun getMainModels(){
        mainHomeAdapter = MainHomeAdapter(requireActivity())
        binding.productRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.productRecycler.adapter = mainHomeAdapter
        mainViewModel.mainModels().observe(requireActivity()){
           lifecycleScope.launchWhenStarted {
               mainHomeAdapter.submitData(it)
           }
        }
    }


}