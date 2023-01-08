package com.example.shopapp.featureModules.orderModule.ui.activities

import android.app.PendingIntent.getActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopapp.R
import com.example.shopapp.application.ShopApplication
import com.example.shopapp.databinding.ActivityOrdersBinding
import com.example.shopapp.featureModules.orderModule.di.DaggerOrderComponent
import com.example.shopapp.featureModules.orderModule.ui.adapters.OrderProductAdapter
import com.example.shopapp.featureModules.orderModule.viewModels.OrderViewModel
import com.example.shopapp.utility.Constants
import com.example.shopapp.utility.DataStoreManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class OrdersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrdersBinding
    private lateinit var orderViewModel: OrderViewModel
    private var orderId: String = ""

    @Inject
    lateinit var dataStoreManager: DataStoreManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_orders)

        orderViewModel = ViewModelProvider(this)[OrderViewModel::class.java]

        DaggerOrderComponent.builder().appComponent((application as ShopApplication).applicationComponent()).build().also {
            it.inject(this)
            it.inject(orderViewModel)
        }
        orderId = intent.getStringExtra(Constants.INTENT_PARAMS.ORDER_ID).toString()



        buildRecyclerView()

    }

    private fun buildStepView(index:Int){
        val steps: List<String> = arrayListOf("Pending","Order Confirmed","Out for delivery","Delivered")

        binding.stepView
            .setStepViewTexts(steps)//总步骤
            .setTextSize(13)//set textSize
            .setStepsViewIndicatorComplectingPosition(index)
            .reverseDraw(false)
            .setLinePaddingProportion(1.5f)
            .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(this, R.color.green_selected))//设置StepsViewIndicator完成线的颜色
            .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(this, R.color.green_unselected))//设置StepsViewIndicator未完成线的颜色
            .setStepViewComplectedTextColor(ContextCompat.getColor(this, R.color.black))//设置StepsView text完成线的颜色
            .setStepViewUnComplectedTextColor(ContextCompat.getColor(this, R.color.bluegray))//设置StepsView text未完成线的颜色
            .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this, R.drawable.step_selected))//设置StepsViewIndicator CompleteIcon
            .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this, R.drawable.step_unselected))
            .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this, R.drawable.step_selected))
            //设置StepsViewIndicator DefaultIcon
    }

    private fun buildRecyclerView(){
        orderViewModel.fetchOrder(orderId).observe(this){
            if (it.success){
                val adapter =OrderProductAdapter(it.response!!.products,this)
                binding.productsRecycler.layoutManager= LinearLayoutManager(this)
                binding.productsRecycler.adapter = adapter
                binding.orderId.text = "Order Id:"+it.response.orderId
                val user = runBlocking { dataStoreManager.user.first() }
                binding.username.text = user.userName
                binding.userAddress.text = it.response.address
                binding.sellingPrice.text = "$${it.response.amount?.minus(50)}"
                binding.totalPrice.text = "$${it.response.amount}"

                when(it.response.status){
                    "Pending"-> buildStepView(0)
                    "Order Confirmed"-> buildStepView(1)
                    "Out For Delivery"-> buildStepView(2)
                    "Delivered"-> buildStepView(3)

                }




                binding.modeOfPayment.text = if (it.response.modeOfPayment == "Online") "•Online: $${it.response.amount.toString()}" else "•Cash on Delivery: $${it.response.amount.toString()}"
            }

        }
    }
}