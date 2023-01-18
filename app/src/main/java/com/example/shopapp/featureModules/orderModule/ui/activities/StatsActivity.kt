package com.example.shopapp.featureModules.orderModule.ui.activities

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopapp.R
import com.example.shopapp.application.ShopApplication
import com.example.shopapp.databinding.ActivityStatsBinding
import com.example.shopapp.featureModules.orderModule.di.DaggerOrderComponent
import com.example.shopapp.featureModules.orderModule.ui.adapters.ChipsAdapter
import com.example.shopapp.featureModules.orderModule.viewModels.OrderViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate


class StatsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStatsBinding
    private lateinit var chipsAdapter: ChipsAdapter
    private lateinit var orderViewModel: OrderViewModel
    private var barEntriesArrayList: MutableList<BarEntry> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_stats)
        orderViewModel = ViewModelProvider(this)[OrderViewModel::class.java]

        DaggerOrderComponent.builder().appComponent((application as ShopApplication).applicationComponent()).build().also {
            it.inject(this)
            it.inject(orderViewModel)
        }

        buildChipsRecycler()


        getBarEntries("Last 7 days")

        chipsAdapter.onClickListener(object : ChipsAdapter.OnClickListener{
            override fun onClickListener(item: String) {
                getBarEntries(item)
            }

        })
    }

    private fun buildChipsRecycler(){
        val timeLine:List<String> = arrayListOf("Last 7 days","Last 15 days","Last month","Last year")
        chipsAdapter= ChipsAdapter(timeLine,this)
        binding.rvTime.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.rvTime.adapter=chipsAdapter
    }
    private fun getBarEntries(item: String) {
        // creating a new array list

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.

        binding.cpPbar.visibility = View.VISIBLE
        binding.idBarChart.visibility = View.GONE

        orderViewModel.fetchStats(item).observe(this){
            Log.d("BAMSTATS",it.toString())
            if (it.success){
                binding.cpPbar.visibility = View.GONE
                binding.idBarChart.visibility = View.VISIBLE
                barEntriesArrayList.clear()
                for (entry in it.response!!){
                    barEntriesArrayList.add(BarEntry(entry.id!!,entry.total!!))

                    val barDataSet = BarDataSet(barEntriesArrayList, "Shop App")

                    // creating a new bar data and
                    // passing our bar data set.

                    // creating a new bar data and
                    // passing our bar data set.
                    val barData = BarData(barDataSet)

                    // below line is to set data
                    // to our bar chart.

                    // below line is to set data
                    // to our bar chart.
                    binding.idBarChart.setData(barData)

                    // adding color to our bar data set.

                    // adding color to our bar data set.
                    val colors: ArrayList<Int> = ArrayList()
                    for (color in ColorTemplate.JOYFUL_COLORS) {
                        colors.add(color)
                    }
                    barDataSet.colors = colors

                    // setting text color.

                    // setting text color.
                    barDataSet.valueTextColor = Color.BLACK

                    // setting text size

                    // setting text size
                    barDataSet.valueTextSize = 16f
                    binding.idBarChart.description.isEnabled = false

                    binding.idBarChart.axisLeft.isEnabled = false
                    binding.idBarChart.axisRight.isEnabled = false
                    binding.idBarChart.animateY(1000)
                }
            }
        }




    }
}