package com.example.shopapp.featureModules.productModule.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopapp.R
import com.example.shopapp.application.ShopApplication
import com.example.shopapp.databinding.ActivitySearchBinding
import com.example.shopapp.featureModules.productModule.di.DaggerProductComponent
import com.example.shopapp.featureModules.productModule.ui.adapters.ProductsPagingAdapter
import com.example.shopapp.featureModules.productModule.viewModels.ProductViewModel

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var productViewModel: ProductViewModel
    private lateinit var adapter: ProductsPagingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_search)

        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        DaggerProductComponent.builder().appComponent((application as ShopApplication).applicationComponent()).build().also {
            it.inject(this)
            it.inject(productViewModel)
        }
        adapter = ProductsPagingAdapter(this)
        binding.productsRecycler.layoutManager = LinearLayoutManager(this)
        binding.productsRecycler.adapter = adapter
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
               // TODO("Not yet implemented")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    binding.productsRecycler.visibility = View.GONE

                }
                else{
                    binding.productsRecycler.visibility = View.VISIBLE
                    searchProducts(newText)

                }
                return false
            }

        })
    }

    private fun searchProducts(title:String){
        productViewModel.getAllProducts(title,null).observe(this){
            lifecycleScope.launchWhenStarted {
                adapter.submitData(it)
            }
        }
    }
}