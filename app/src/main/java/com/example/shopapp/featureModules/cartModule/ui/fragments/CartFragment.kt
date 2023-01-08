package com.example.shopapp.featureModules.cartModule.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopapp.R
import com.example.shopapp.application.ShopApplication
import com.example.shopapp.databinding.CheckoutLayoutBinding
import com.example.shopapp.databinding.FragmentCartBinding
import com.example.shopapp.featureModules.cartModule.di.DaggerCartComponent
import com.example.shopapp.featureModules.cartModule.models.CartProductModel
import com.example.shopapp.featureModules.orderModule.ui.activities.PaymentActivity
import com.example.shopapp.featureModules.cartModule.ui.adapters.CartAdapter
import com.example.shopapp.featureModules.cartModule.viewModels.CartViewModel
import com.example.shopapp.featureModules.orderModule.di.DaggerOrderComponent
import com.example.shopapp.featureModules.orderModule.models.OrderModel
import com.example.shopapp.featureModules.orderModule.models.OrderProductModel
import com.example.shopapp.featureModules.orderModule.ui.activities.OrderSuccessfulActivity
import com.example.shopapp.featureModules.orderModule.viewModels.OrderViewModel
import com.example.shopapp.utility.Constants
import com.example.shopapp.utility.DataStoreManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


class CartFragment : Fragment() {


    private lateinit var binding: FragmentCartBinding
    private lateinit var cartViewModel: CartViewModel
    private lateinit var orderViewModel: OrderViewModel
    private lateinit var adapter: CartAdapter
    private var products: List<OrderProductModel> = arrayListOf()
    private var amount: Int=0
    private var address: String = ""
    private var isOnlinePayment: Boolean = false

    @Inject
    lateinit var dataStoreManager: DataStoreManager

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
            showCheckoutBottomSheet()
        }
        binding.cpPbar.visibility = View.VISIBLE

    }

    private fun buildRecyclerView() {
        binding.cpPbar.visibility = View.VISIBLE

        cartViewModel.getCart().observe(requireActivity()) {
            if (it.success) {
                if (it.response != null) {

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

                        amount = it.response.bill!!
                        binding.productRecycler.visibility = View.VISIBLE
                        binding.cartEmpty.visibility = View.GONE
                        binding.shopNowBtn.visibility = View.GONE
                        binding.cpPbar.visibility = View.GONE
                        binding.checkoutLayout.visibility = View.VISIBLE
                        adapter = CartAdapter(it.response.products, requireContext())
                        binding.productRecycler.layoutManager =
                            LinearLayoutManager(requireContext())
                        binding.productRecycler.adapter = adapter
                        binding.totalBill.setText("$" + it.response.bill)

                        adapter.onPlusClickListener(object : CartAdapter.OnPlusClickListener {
                            override fun onPlusClicked(cartProductModel: CartProductModel) {
                                cartProductModel.quantity = 1
                                modifyCart(cartProductModel)

                                Handler(Looper.getMainLooper()).postDelayed({
                                    buildRecyclerView()
                                }, 800)

                            }


                        })

                        adapter.onMinusClickListener(object : CartAdapter.OnMinusClickListener {
                            override fun onMinusClicked(cartProductModel: CartProductModel) {
                                cartProductModel.quantity = -1
                                modifyCart(cartProductModel)
                                Handler(Looper.getMainLooper()).postDelayed({
                                    buildRecyclerView()
                                }, 800)

                            }

                        })


                    } else {
                        binding.productRecycler.visibility = View.GONE
                        binding.cartEmpty.visibility = View.VISIBLE
                        binding.shopNowBtn.visibility = View.VISIBLE
                        binding.cpPbar.visibility = View.GONE

                        binding.checkoutLayout.visibility = View.GONE
                    }
                } else {
                    binding.productRecycler.visibility = View.GONE
                    binding.cartEmpty.visibility = View.VISIBLE
                    binding.shopNowBtn.visibility = View.VISIBLE
                    binding.cpPbar.visibility = View.GONE

                    binding.checkoutLayout.visibility = View.GONE
                }
            }
            else{
                binding.productRecycler.visibility = View.GONE
                binding.cartEmpty.visibility = View.VISIBLE
                binding.shopNowBtn.visibility = View.VISIBLE
                binding.cpPbar.visibility = View.GONE

                binding.checkoutLayout.visibility = View.GONE
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

    private fun showCheckoutBottomSheet(){
        val bottomSheetDialog = BottomSheetDialog(requireActivity())
        val binding = CheckoutLayoutBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(binding.root)

        val user = runBlocking { dataStoreManager.user.first() }
        binding.username.text = user.userName

        binding.paymentRadioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
               when(p1){
                   R.id.online_payment->{
                       isOnlinePayment = true
                   }
                   R.id.cod->{
                       isOnlinePayment = false

                   }
               }
            }

        })

        binding.btnProceed.setOnClickListener {
            address = binding.address.text.toString()
            placeOrder()
            bottomSheetDialog.dismiss()

        }

        bottomSheetDialog.show()
    }

    private fun placeOrder(){



        if (isOnlinePayment){
            val orderModel = OrderModel(
                products = products,
                amount = amount,
                address = address,
                modeOfPayment = "Online"
            )
            val intent = Intent(requireActivity(), PaymentActivity::class.java)
            intent.putExtra(Constants.INTENT_PARAMS.ORDER_OBJ,orderModel)
            startActivity(intent)
        }
        else{
            val orderModel = OrderModel(
                products = products,
                amount = amount,
                address = address,
                modeOfPayment = "Cash on delivery"
            )
            orderViewModel.placeOrder(orderModel).observe(requireActivity()){
                if (it.success){
                    val intent = Intent(requireActivity(),OrderSuccessfulActivity::class.java)
                    intent.putExtra(Constants.INTENT_PARAMS.ORDER_ID,it.response?.orderId)
                    startActivity(intent)
                    Toast.makeText(requireContext(),"Successfully placed order",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()

                }
            }
        }


    }

    override fun onResume() {
        super.onResume()
        buildRecyclerView()
    }


}