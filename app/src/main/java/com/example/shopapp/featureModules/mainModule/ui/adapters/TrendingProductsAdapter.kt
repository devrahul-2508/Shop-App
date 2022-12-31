package com.example.shopapp.featureModules.mainModule.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopapp.R
import com.example.shopapp.databinding.ItemProductBinding
import com.example.shopapp.featureModules.productModule.models.ProductModel
import com.example.shopapp.featureModules.productModule.ui.activities.ProductDetailsActivity
import com.example.shopapp.utility.Constants

class TrendingProductsAdapter(val products:List<ProductModel>, private val context: Context):RecyclerView.Adapter<TrendingProductsAdapter.TrendingProductsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingProductsViewHolder {
        return TrendingProductsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_product,parent,false
        ))
    }

    override fun onBindViewHolder(holder: TrendingProductsViewHolder, position: Int) {
        val item = products[position]
        with(holder.binding){

            Glide.with(context).load(item?.img).into(productImage)
            productTitle.text = item?.title
            productDescription.text = item?.desc
            productPrice.text = "$"+item?.price


        }

        holder.itemView.setOnClickListener{
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra(Constants.INTENT_PARAMS.PRODUCT_ID,item?.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class TrendingProductsViewHolder(val binding:ItemProductBinding): RecyclerView.ViewHolder(binding.root)


}