package com.example.shopapp.featureModules.cartModule.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopapp.R
import com.example.shopapp.application.ShopApplication
import com.example.shopapp.databinding.FragmentCartBinding
import com.example.shopapp.featureModules.cartModule.di.DaggerCartComponent
import com.example.shopapp.featureModules.cartModule.ui.adapters.CartAdapter
import com.example.shopapp.featureModules.cartModule.viewModels.CartViewModel


class CartFragment : Fragment() {


    private lateinit var binding: FragmentCartBinding
    private lateinit var cartViewModel: CartViewModel
    private lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartViewModel = ViewModelProvider(requireActivity())[CartViewModel::class.java]

        DaggerCartComponent.builder().appComponent((requireActivity().application as ShopApplication).applicationComponent()).build().also {
            it.inject(this)
            it.inject(cartViewModel)
        }

        buildRecyclerView()

    }

    private fun buildRecyclerView(){
        cartViewModel.getCart().observe(requireActivity()){
            if (it.success){
                if (it.response!=null){
                    adapter = CartAdapter(it.response.products,requireContext())
                    binding.productRecycler.layoutManager = LinearLayoutManager(requireContext())
                    binding.productRecycler.adapter = adapter
                }
                else{

                }

            }
        }
    }


}