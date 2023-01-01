package com.example.shopapp.featureModules.mainModule.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.R
import com.example.shopapp.databinding.ItemTopSellingProductBinding
import com.example.shopapp.databinding.OnSaleProductsLayoutBinding
import com.example.shopapp.databinding.TopSellingProductsLayoutBinding
import com.example.shopapp.databinding.TrendingProductsLayoutBinding
import com.example.shopapp.featureModules.mainModule.models.MainModels
import com.example.shopapp.featureModules.productModule.models.ProductModel

class MainHomeAdapter(private val context: Context):PagingDataAdapter<MainModels,RecyclerView.ViewHolder>(MainModelsComparator) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return when(viewType){
            1->{
                TopSellingViewHolder(
                    DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.top_selling_products_layout,parent,false)
                )
            }
            2->{
               TrendingViewHolder(
                    DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.trending_products_layout,parent,false)

                )
            }
            else->{
                OnSaleViewHolder(
                    DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.on_sale_products_layout,parent,false)

                )
            }
        }
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       val item = getItem(position)
        when(item?.viewType){
            600->{
                val h = holder as TopSellingViewHolder
                val topSellingProductsAdapter = TopSellingProductsAdapter(item.topSellingProductModel?.products!!, context)
                h.topSellingProductBinding.productRecycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                h.topSellingProductBinding.productRecycler.adapter = topSellingProductsAdapter

            }

            700->{
                val h = holder as TrendingViewHolder
                val trendingProductsAdapter = TrendingProductsAdapter(item.trendingProductModel?.products!!,context)
                h.trendingProductsLayoutBinding.productRecycler.layoutManager=
                    GridLayoutManager(context,2, LinearLayoutManager.VERTICAL,false)
                h.trendingProductsLayoutBinding.productRecycler.adapter=trendingProductsAdapter
            }
            800->{
                val h = holder as OnSaleViewHolder
                val onSaleProductsAdapter = OnSaleProductsAdapter(item.onSaleProductModel?.products!!,context)
                h.onSaleProductsLayoutBinding.productRecycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                h.onSaleProductsLayoutBinding.productRecycler.adapter = onSaleProductsAdapter
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        //return super.getItemViewType(position)
        val item = getItem(position)
        return when(item?.viewType){
            600->{
                1
            }
            700->{
                2
            }
            else->{
                3
            }

        }

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

