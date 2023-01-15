package com.example.shopapp.featureModules.orderModule.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.shopapp.R
import com.example.shopapp.databinding.ItemTimeChipBinding

class ChipsAdapter(private val timeLine: List<String>,private val context: Context): RecyclerView.Adapter<ChipsAdapter.ChipsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipsViewHolder {
        return ChipsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.item_time_chip,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ChipsViewHolder, position: Int) {
        val item = timeLine[position]
        with(holder.binding){
            timeLabel.text = item
        }
    }
    override fun getItemCount(): Int {
        return timeLine.size
    }



    inner class ChipsViewHolder(val binding: ItemTimeChipBinding):RecyclerView.ViewHolder(binding.root)


}