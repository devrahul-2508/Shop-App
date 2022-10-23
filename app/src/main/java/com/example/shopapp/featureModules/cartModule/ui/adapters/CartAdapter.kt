package com.example.shopapp.featureModules.cartModule.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopapp.R
import com.example.shopapp.databinding.ItemCartProductLayoutBinding
import com.example.shopapp.featureModules.cartModule.models.CartProductModel

class CartAdapter(private val cartProductList: List<CartProductModel>,private val context: Context): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_cart_product_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       with(holder.binding){
           val item = cartProductList[position]
            productTitle.text = item.title
            productDescription.text = item.description
            productPrice.text = item.price.toString()
           Glide.with(context).load(item.img).into(productImage)
       }
    }

    override fun getItemCount(): Int {
        return cartProductList.size
    }





    inner class ViewHolder(val binding: ItemCartProductLayoutBinding): RecyclerView.ViewHolder(binding.root)


}