package com.example.shopapp.featureModules.orderModule.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopapp.R
import com.example.shopapp.application.ShopApplication
import com.example.shopapp.databinding.ActivityAdminOrdersBinding
import com.example.shopapp.featureModules.mainModule.viewModels.MainViewModel
import com.example.shopapp.featureModules.orderModule.di.DaggerOrderComponent
import com.example.shopapp.featureModules.orderModule.ui.adapters.AdminOrdersPagingAdapter
import com.example.shopapp.featureModules.orderModule.viewModels.OrderViewModel

class AdminOrdersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminOrdersBinding
    private lateinit var orderViewModel: OrderViewModel
    private lateinit var adapter: AdminOrdersPagingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_orders)

        orderViewModel = ViewModelProvider(this)[OrderViewModel::class.java]

        DaggerOrderComponent.builder().appComponent((application as ShopApplication).applicationComponent()).build().also {
            it.inject(this)
            it.inject(orderViewModel)
        }



        buildRecyclerView()
    }

    private fun buildRecyclerView(){

        adapter = AdminOrdersPagingAdapter(this)
        binding.orderRecycler.layoutManager = LinearLayoutManager(this)
        binding.orderRecycler.adapter = adapter
        orderViewModel.fetchAdminOrders().observe(this){
            Log.d("BAM",it.toString())
            lifecycleScope.launchWhenStarted {
                adapter.submitData(it)
            }
        }
    }
}