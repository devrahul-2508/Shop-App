package com.example.shopapp.featureModules.orderModule.ui.activities

import android.app.PendingIntent.getActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.shopapp.R
import com.example.shopapp.application.ShopApplication
import com.example.shopapp.databinding.ActivityOrdersBinding
import com.example.shopapp.featureModules.orderModule.di.DaggerOrderComponent
import com.example.shopapp.featureModules.orderModule.viewModels.OrderViewModel

class OrdersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrdersBinding
    private lateinit var orderViewModel: OrderViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_orders)

        orderViewModel = ViewModelProvider(this)[OrderViewModel::class.java]

        DaggerOrderComponent.builder().appComponent((application as ShopApplication).applicationComponent()).build().also {
            it.inject(this)
            it.inject(orderViewModel)
        }

        buildStepView()
    }

    private fun buildStepView(){
        val steps: List<String> = arrayListOf("Pending","Order Confirmed","Out for delivery","Delivered")

        binding.stepView
            .setStepViewTexts(steps)//总步骤
            .setTextSize(13)//set textSize
            .setStepsViewIndicatorComplectingPosition(2)
            .reverseDraw(false)
            .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(this, R.color.green_selected))//设置StepsViewIndicator完成线的颜色
            .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(this, R.color.green_unselected))//设置StepsViewIndicator未完成线的颜色
            .setStepViewComplectedTextColor(ContextCompat.getColor(this, R.color.black))//设置StepsView text完成线的颜色
            .setStepViewUnComplectedTextColor(ContextCompat.getColor(this, R.color.bluegray))//设置StepsView text未完成线的颜色
            .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this, R.drawable.step_selected))//设置StepsViewIndicator CompleteIcon
            .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this, R.drawable.step_unselected))
            //设置StepsViewIndicator DefaultIcon
    }
}