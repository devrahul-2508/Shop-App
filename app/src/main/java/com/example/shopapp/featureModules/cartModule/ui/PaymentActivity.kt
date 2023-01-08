package com.example.shopapp.featureModules.cartModule.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.shopapp.R
import com.example.shopapp.application.ShopApplication
import com.example.shopapp.databinding.ActivityPaymentBinding
import com.example.shopapp.featureModules.cartModule.di.DaggerCartComponent
import com.example.shopapp.featureModules.cartModule.viewModels.CartViewModel
import com.example.shopapp.featureModules.orderModule.models.OrderModel
import com.example.shopapp.utility.Constants
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONException
import org.json.JSONObject

class PaymentActivity : AppCompatActivity(),PaymentResultListener {

    private lateinit var binding: ActivityPaymentBinding
    private lateinit var cartViewModel: CartViewModel
    private lateinit var orderModel: OrderModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_payment)

        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]

        DaggerCartComponent.builder().appComponent((application as ShopApplication).applicationComponent()).build().also {
            it.inject(cartViewModel)
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
        finish()
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(this,"Payment Failed",Toast.LENGTH_SHORT).show()
        finish()
    }
}