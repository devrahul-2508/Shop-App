package com.example.shopapp.featureModules.mainModule.ui.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.databinding.ItemTopSellingProductBinding
import com.example.shopapp.databinding.OnSaleProductsLayoutBinding
import com.example.shopapp.databinding.TopSellingProductsLayoutBinding
import com.example.shopapp.databinding.TrendingProductsLayoutBinding
import com.example.shopapp.featureModules.mainModule.models.MainModels
import com.example.shopapp.featureModules.productModule.models.ProductModel

class MainHomeAdapter(private val mainModels: List<MainModels>):PagingDataAdapter<MainModels,RecyclerView.ViewHolder>(MainModelsComparator) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }



    inner class TopSellingViewHolder(val topSellingProductBinding: TopSellingProductsLayoutBinding):RecyclerView.ViewHolder(topSellingProductBinding.root)

    inner class TrendingViewHolder(val trendingProductsLayoutBinding: TrendingProductsLayoutBinding): RecyclerView.ViewHolder(trendingProductsLayoutBinding.root)

    inner class OnSaleViewHolder(val onSaleProductsLayoutBinding:OnSaleProductsLayoutBinding): RecyclerView.ViewHolder(onSaleProductsLayoutBinding.root)


    object MainModelsComparator : DiffUtil.ItemCallback<MainModels>() {
        override fun areItemsTheSame(oldItem: MainModels, newItem: MainModels): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MainModels, newItem: MainModels): Boolean {
            return oldItem == newItem
        }
    }


}

