package com.example.shopapp.featureModules.orderModule.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopapp.R
import com.example.shopapp.databinding.ItemOrderStatusLayoutBinding
import com.example.shopapp.featureModules.orderModule.models.OrderModel
import com.example.shopapp.featureModules.orderModule.ui.activities.OrdersActivity
import com.example.shopapp.featureModules.orderModule.viewModels.OrderViewModel
import com.example.shopapp.utility.Constants

class AdminOrdersPagingAdapter(private val orderViewModel: OrderViewModel,private val context: Context): PagingDataAdapter<OrderModel,AdminOrdersPagingAdapter.ViewHolder>(OrderComparator) {



   var status: String = ""

    inner class ViewHolder(val binding: ItemOrderStatusLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            var orderTitle: String = ""

            orderId.text = "Order ID:\n" + item?.orderId
            orderPrice.text = "$" + item?.amount.toString()

            Glide.with(context).load(item!!.products[0].img).into(img1)
            Glide.with(context).load(item!!.products[1].img).into(img2)
            holder.itemView.setOnClickListener {
                val intent = Intent(context, OrdersActivity::class.java)
                intent.putExtra(Constants.INTENT_PARAMS.ORDER_ID, item.orderId)
                context.startActivity(intent)
            }

            val size = item.products.size
            val limit = 2
            for (i in 0 until limit) {
                orderTitle += if (i == limit - 1) {
                    item.products[i].title + " "
                } else {
                    item.products[i].title + ","

                }

            }
            if (size > limit) {
                if (size - limit == 1) {
                    orderTitle += "+${size - limit} other"
                } else {
                    orderTitle += "+${size - limit} others"

                }

            }

            productsTitle.text = orderTitle

            val statuses : ArrayList<String> = arrayListOf("Pending","Order Confirmed","Out for Delivery","Delivered")

            val arrayAdapter = ArrayAdapter(
                context,
                android.R.layout.simple_spinner_item,
                statuses
            )

            arrayAdapter.setDropDownViewResource(
                android.R.layout
                    .simple_spinner_dropdown_item);

            statusSpinner.adapter = arrayAdapter

            //SET POSITION OF SPINNER

            when (item.status){
                "Pending"->{
                    statusSpinner.setSelection(0)
                }
                "Order Confirmed"->{
                    statusSpinner.setSelection(1)
                }
                "Out for Delivery"->{
                    statusSpinner.setSelection(2)
                }
                "Delivered"->{
                    statusSpinner.setSelection(3)
                }
            }



            var isSpinnerTouched = false

            statusSpinner.setOnTouchListener { _, _ ->
                isSpinnerTouched = true
                false
            }



            statusSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (!isSpinnerTouched){
                        return
                    }
                    else{
                        when(statusSpinner.getItemAtPosition(p2)){
                            "Pending"->{
                                status = "Pending"

                            }
                            "Order Confirmed"->{
                                status = "Order Confirmed"
                            }
                            "Out for Delivery"->{
                                status = "Out for delivery"
                            }
                            "Delivered"->{
                                status = "Delivered"
                            }
                        }
                        item.status = status
                        updateStatus(item)
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_order_status_layout,
                parent,
                false
            )
        )
    }

    object OrderComparator : DiffUtil.ItemCallback<OrderModel>() {
        override fun areItemsTheSame(oldItem: OrderModel, newItem: OrderModel): Boolean {
            // Id is unique.
            return oldItem.orderId == newItem.orderId
        }

        override fun areContentsTheSame(oldItem: OrderModel, newItem: OrderModel): Boolean {
            return oldItem == newItem
        }
    }

    private fun updateStatus(orderModel: OrderModel){
        orderViewModel.updateAdminOrders(orderModel).observe(context as LifecycleOwner){
            if (it.success){
                Toast.makeText(context,"Successfull",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context,"Failure",Toast.LENGTH_SHORT).show()

            }
        }
    }
}