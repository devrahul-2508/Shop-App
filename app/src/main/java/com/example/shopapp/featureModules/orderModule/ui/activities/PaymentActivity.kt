package com.example.shopapp.featureModules.orderModule.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.shopapp.R
import com.example.shopapp.application.ShopApplication
import com.example.shopapp.databinding.ActivityPaymentBinding
import com.example.shopapp.featureModules.cartModule.di.DaggerCartComponent
import com.example.shopapp.featureModules.cartModule.viewModels.CartViewModel
import com.example.shopapp.featureModules.orderModule.di.DaggerOrderComponent
import com.example.shopapp.featureModules.orderModule.models.OrderModel
import com.example.shopapp.featureModules.orderModule.viewModels.OrderViewModel
import com.example.shopapp.utility.Constants
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONException
import org.json.JSONObject

class PaymentActivity : AppCompatActivity(),PaymentResultListener {

    private lateinit var binding: ActivityPaymentBinding
    private lateinit var orderViewModel: OrderViewModel
    private lateinit var orderModel: OrderModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_payment)

        orderViewModel = ViewModelProvider(this)[OrderViewModel::class.java]

        DaggerOrderComponent.builder().appComponent((application as ShopApplication).applicationComponent()).build().also {
            it.inject(orderViewModel)
            it.inject(this)
        }

        orderModel = intent.getSerializableExtra(Constants.INTENT_PARAMS.ORDER_OBJ) as OrderModel

        Handler(Looper.getMainLooper()).postDelayed({
            startCheckOut()
        }, 800)
    }

    private fun startCheckOut(){
        val checkout: Checkout = Checkout()
        checkout.setKeyID("rzp_test_Y5M1990yVovgi5")
       val rzpAmount = orderModel.amount.toString() + "00"
        val obj = JSONObject()
        try {
            obj.put("name", "Shop App")
            obj.put("description", "Test Payment")
            obj.put("theme.colour", "#0093DD")
            obj.put("currency", "INR")
            obj.put("amount", rzpAmount)
            obj.put("prefill.contact", "1234567890")
            obj.put("prefill.email", "rahul@shopapp.com")
            checkout.open(this, obj)


        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(p0: String?) {
        Toast.makeText(this,"Payment Success",Toast.LENGTH_SHORT).show()
        orderViewModel.placeOrder(orderModel).observe(this){
            if (it.success){
                val intent = Intent(this, OrderSuccessfulActivity::class.java)
                intent.putExtra(Constants.INTENT_PARAMS.ORDER_ID,it.response?.orderId)
                startActivity(intent)
                finish()
                Toast.makeText(this,"Successfully placed order",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(this,"Payment Failed",Toast.LENGTH_SHORT).show()
        finish()
    }
}