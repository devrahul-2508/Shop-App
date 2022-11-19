package com.example.shopapp.featureModules.orderModule.ui.adapters

import android.content.Context
import android.content.Intent
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
import com.example.shopapp.featureModules.orderModule.ui.activities.OrdersActivity
import com.example.shopapp.featureModules.productModule.models.ProductModel
import com.example.shopapp.utility.Constants

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
            var orderTitle: String = ""
            orderId.text = "Order ID:\n"+item?.orderId
            orderPrice.text = "$"+item?.amount.toString()
            when(item?.status){
                "Pending"->{
                    orderStatus.text = item.status
                    orderStatus.setTextColor(context.resources.getColor(R.color.pending))
                }
                "Shipped"->{
                    orderStatus.text = item.status
                    orderStatus.setTextColor(context.resources.getColor(R.color.green))
                }
                "Delivered"->{
                    orderStatus.text = item.status
                    orderStatus.setTextColor(context.resources.getColor(R.color.green))
                }
                else->{
                    "Cant Find"
                }
            }
            Glide.with(context).load(item!!.products[0].img).into(img1)
            Glide.with(context).load(item!!.products[1].img).into(img2)
            holder.itemView.setOnClickListener{
                val intent = Intent(context,OrdersActivity::class.java)
                intent.putExtra(Constants.INTENT_PARAMS.ORDER_ID,item.orderId)
                context.startActivity(intent)
            }

            val size = item.products.size
            val limit = 2
            for(i in 0 until limit){
                orderTitle += if(i == limit-1){
                    item.products[i].title+" "
                } else{
                    item.products[i].title+","

                }

            }
            if (size>limit){
                if(size-limit == 1){
                    orderTitle+="+${size-limit} other"
                }
                else{
                    orderTitle+="+${size-limit} others"

                }

            }

            productsTitle.text = orderTitle
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