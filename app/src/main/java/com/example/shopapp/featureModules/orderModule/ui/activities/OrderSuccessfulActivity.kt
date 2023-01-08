package com.example.shopapp.featureModules.orderModule.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.shopapp.R
import com.example.shopapp.databinding.ActivityOrderSuccessfulBinding
import com.example.shopapp.utility.Constants

class OrderSuccessfulActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderSuccessfulBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_order_successful)

        val orderId = intent.getStringExtra(Constants.INTENT_PARAMS.ORDER_ID)

        binding.orderId.text = orderId

        binding.shopMore.setOnClickListener {
            finish()
        }
    }
}