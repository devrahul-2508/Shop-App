package com.example.shopapp.featureModules.orderModule.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopapp.R
import com.example.shopapp.databinding.ItemOrderProductBinding
import com.example.shopapp.featureModules.orderModule.models.OrderProductModel

class OrderProductAdapter(private val productsList: List<OrderProductModel>,private val context: Context): RecyclerView.Adapter<OrderProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.item_order_product,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding){
            val item = productsList[position]
            productTitle.text = item.title
            productDescription.text = item.description
            productPrice.text = "$"+item.price

            Glide.with(context).load(item.img).into(productImage)
        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }




    inner class ViewHolder(val binding: ItemOrderProductBinding): RecyclerView.ViewHolder(binding.root)


}