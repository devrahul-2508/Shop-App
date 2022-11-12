package com.example.shopapp.featureModules.cartModule.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopapp.R
import com.example.shopapp.application.ShopApplication
import com.example.shopapp.databinding.FragmentCartBinding
import com.example.shopapp.featureModules.cartModule.di.DaggerCartComponent
import com.example.shopapp.featureModules.cartModule.models.CartProductModel
import com.example.shopapp.featureModules.cartModule.ui.adapters.CartAdapter
import com.example.shopapp.featureModules.cartModule.viewModels.CartViewModel
import com.example.shopapp.featureModules.orderModule.di.DaggerOrderComponent
import com.example.shopapp.featureModules.orderModule.models.OrderModel
import com.example.shopapp.featureModules.orderModule.models.OrderProductModel
import com.example.shopapp.featureModules.orderModule.viewModels.OrderViewModel


class CartFragment : Fragment() {


    private lateinit var binding: FragmentCartBinding
    private lateinit var cartViewModel: CartViewModel
    private lateinit var orderViewModel: OrderViewModel
    private lateinit var adapter: CartAdapter
    private var products: List<OrderProductModel> = arrayListOf()

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
        orderViewModel = ViewModelProvider(requireActivity())[OrderViewModel::class.java]

        DaggerCartComponent.builder().appComponent((requireActivity().application as ShopApplication).applicationComponent()).build().also {
            it.inject(this)
            it.inject(cartViewModel)
        }

        DaggerOrderComponent.builder().appComponent((requireActivity().application as ShopApplication).applicationComponent()).build().also {
            it.inject(this)
            it.inject(orderViewModel)
        }



        buildRecyclerView()

        binding.btnPlaceOrder.setOnClickListener {
            placeOrder()
        }

    }

    private fun buildRecyclerView(){
        cartViewModel.getCart().observe(requireActivity()){
            if (it.success){
                if (it.response!=null) {
                    if (it.response.products.isNotEmpty()) {

                    products = it.response.products.map {
                      OrderProductModel(
                          id = it.id,
                          productId = it.productId,
                          title = it.title,
                          description = it.description,
                          img = it.img,
                          price = it.price,
                          quantity = it.quantity

                      )
                    }
                    binding.productRecycler.visibility = View.VISIBLE
                    binding.cartEmpty.visibility = View.GONE
                    binding.shopNowBtn.visibility = View.GONE
                        binding.checkoutLayout.visibility = View.VISIBLE
                    adapter = CartAdapter(it.response.products, requireContext())
                    binding.productRecycler.layoutManager = LinearLayoutManager(requireContext())
                    binding.productRecycler.adapter = adapter
                    binding.totalBill.setText("$" + it.response.bill)

                    adapter.onPlusClickListener(object : CartAdapter.OnPlusClickListener {
                        override fun onPlusClicked(cartProductModel: CartProductModel) {
                            cartProductModel.quantity = 1
                            modifyCart(cartProductModel)

                            Handler(Looper.getMainLooper()).postDelayed({
                              buildRecyclerView()
                            }, 300)

                        }


                    })

                    adapter.onMinusClickListener(object : CartAdapter.OnMinusClickListener {
                        override fun onMinusClicked(cartProductModel: CartProductModel) {
                            cartProductModel.quantity = -1
                            modifyCart(cartProductModel)
                            Handler(Looper.getMainLooper()).postDelayed({
                                buildRecyclerView()
                            }, 300)

                        }

                    })
                }
                    else{
                        binding.productRecycler.visibility = View.GONE
                        binding.cartEmpty.visibility = View.VISIBLE
                        binding.shopNowBtn.visibility = View.VISIBLE
                        binding.checkoutLayout.visibility = View.GONE
                    }
                }


            }
        }
    }

    private fun modifyCart(cartProductModel: CartProductModel){
        cartViewModel.modifyCart(cartProductModel).observe(this){
            if (it.success){
                Toast.makeText(requireContext(),"Cart Modified Successfully",Toast.LENGTH_SHORT).show()


            }
            else{
                Toast.makeText(requireContext(),"Some error Occurred",Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun placeOrder(){
        val orderModel = OrderModel(
            products = products
        )
        orderViewModel.placeOrder(orderModel).observe(requireActivity()){
            if (it.success){
                Toast.makeText(requireContext(),"Successfully placed order",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()

            }
        }
    }


}