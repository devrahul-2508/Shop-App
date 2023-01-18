package com.example.shopapp.featureModules.orderModule.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.shopapp.R
import com.example.shopapp.databinding.ItemTimeChipBinding

class ChipsAdapter(private val timeLine: List<String>,private val context: Context): RecyclerView.Adapter<ChipsAdapter.ChipsViewHolder>() {


    private var listener: OnClickListener?=null

    var selectedPosition: Int=0



    interface OnClickListener{
        fun onClickListener(item: String)
    }

    fun onClickListener(listener : OnClickListener){
        this.listener = listener
    }



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


            if (selectedPosition == position){
                timeView.setCardBackgroundColor(ContextCompat.getColor(context,R.color.chip_colour_selected))
                timeLabel.setTextColor(Color.WHITE)
            }
            else{
                timeView.setCardBackgroundColor(ContextCompat.getColor(context,R.color.chip_colour_unselected))
                timeLabel.setTextColor(Color.BLACK)

            }
        }
    }
    override fun getItemCount(): Int {
        return timeLine.size
    }



    inner class ChipsViewHolder(val binding: ItemTimeChipBinding):RecyclerView.ViewHolder(binding.root){
        init {
            binding.timeView.setOnClickListener {
                if (listener!=null){

                    val position = absoluteAdapterPosition
                    selectedPosition = position

                    if(position != RecyclerView.NO_POSITION){
                        listener!!.onClickListener(timeLine[position])
                    }
                }
                notifyDataSetChanged()
            }
        }
    }


}