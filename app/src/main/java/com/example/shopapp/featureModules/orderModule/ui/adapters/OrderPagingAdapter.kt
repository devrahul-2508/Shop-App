package com.example.shopapp.featureModules.orderModule.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopapp.R
import com.example.shopapp.databinding.ItemOrderBinding
import com.example.shopapp.featureModules.orderModule.models.OrderModel
import com.example.shopapp.featureModules.productModule.models.ProductModel

class OrderPagingAdapter(private val context:Context): PagingDataAdapter<OrderModel,OrderPagingAdapter.ViewHolder>(OrderComparator){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.item_order,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding){
            val item = getItem(position)
            orderId.text = "Order ID:\n"+item?.orderId
            orderPrice.text = "$"+item?.amount.toString()
            Glide.with(context).load(item!!.products[0].img).into(img)
        }
    }



inner class ViewHolder(val binding: ItemOrderBinding): RecyclerView.ViewHolder(binding.root)

    object OrderComparator : DiffUtil.ItemCallback<OrderModel>() {
        override fun areItemsTheSame(oldItem: OrderModel, newItem: OrderModel): Boolean {
            // Id is unique.
            return oldItem.orderId == newItem.orderId
        }

        override fun areContentsTheSame(oldItem: OrderModel, newItem: OrderModel): Boolean {
            return oldItem == newItem
        }
    }


}