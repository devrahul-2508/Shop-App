package com.example.shopapp.featureModules.orderModule.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopapp.R
import com.example.shopapp.application.ShopApplication
import com.example.shopapp.databinding.FragmentOrderBinding
import com.example.shopapp.databinding.FragmentProfileBinding
import com.example.shopapp.featureModules.orderModule.di.DaggerOrderComponent
import com.example.shopapp.featureModules.orderModule.ui.adapters.OrderPagingAdapter
import com.example.shopapp.featureModules.orderModule.viewModels.OrderViewModel


class OrderFragment : Fragment() {


    private lateinit var binding: FragmentOrderBinding
    private lateinit var orderViewModel: OrderViewModel
    private lateinit var adapter: OrderPagingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_order, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderViewModel = ViewModelProvider(this)[OrderViewModel::class.java]
        DaggerOrderComponent.builder().appComponent((requireActivity().application as ShopApplication).applicationComponent()).build().also {
            it.inject(this)
            it.inject(orderViewModel)
        }
        buildRecyclerView()

    }

    private fun buildRecyclerView(){
        adapter = OrderPagingAdapter(requireActivity())
        binding.orderRecycler.layoutManager = LinearLayoutManager(requireActivity())
        binding.orderRecycler.adapter = adapter

        orderViewModel.fetchAllOrders().observe(requireActivity()){

          adapter.addLoadStateListener {
                if(it.refresh is LoadState.Loading)
                    binding.cpPbar.visibility = View.VISIBLE
                else if(it.refresh is LoadState.NotLoading)
                    binding.cpPbar.visibility = View.GONE

            }

            lifecycleScope.launchWhenStarted {
                adapter.submitData(it)

            }
        }

    }


    }
