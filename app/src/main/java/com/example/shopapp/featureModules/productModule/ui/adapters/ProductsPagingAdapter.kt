package com.example.shopapp.featureModules.productModule.ui.adapters

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
import com.example.shopapp.databinding.ItemProductBinding
import com.example.shopapp.databinding.ItemSearchProductBinding
import com.example.shopapp.featureModules.productModule.models.ProductModel
import com.example.shopapp.featureModules.productModule.ui.activities.ProductDetailsActivity
import com.example.shopapp.utility.Constants

class ProductsPagingAdapter(private val context: Context): PagingDataAdapter<ProductModel, ProductsPagingAdapter.ProductsViewHolder>(
    ProductComparator
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
       return ProductsViewHolder(
           DataBindingUtil.inflate(
               LayoutInflater.from(context),
               R.layout.item_search_product,
               parent,
               false
           )
       )
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding){

            Glide.with(context).load(item?.img).into(productImage)
            productTitle.text = item?.title
            var categories:String= "In"+' '
            val size = item?.categories?.size
            val limit = 2
            for(i in 0 until size!!){
                categories += if(i == size-1){
                    item?.categories?.get(i) +" "
                } else{
                    item?.categories?.get(i) +","

                }

            }
            productCategory.text = categories



        }

        holder.itemView.setOnClickListener{
            val intent = Intent(context,ProductDetailsActivity::class.java)
            intent.putExtra(Constants.INTENT_PARAMS.PRODUCT_ID,item?.id)
            context.startActivity(intent)
        }

    }




    inner class ProductsViewHolder(val binding: ItemSearchProductBinding): RecyclerView.ViewHolder(binding.root)

    object ProductComparator : DiffUtil.ItemCallback<ProductModel>() {
        override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem == newItem
        }
    }


}