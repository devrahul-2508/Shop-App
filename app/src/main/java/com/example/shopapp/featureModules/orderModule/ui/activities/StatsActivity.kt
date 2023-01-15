package com.example.shopapp.featureModules.orderModule.ui.activities

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopapp.R
import com.example.shopapp.databinding.ActivityStatsBinding
import com.example.shopapp.featureModules.orderModule.ui.adapters.ChipsAdapter
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate


class StatsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStatsBinding
    private lateinit var chipsAdapter: ChipsAdapter
    private var barEntriesArrayList: MutableList<BarEntry> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_stats)

        buildChipsRecycler()


        getBarEntries()
    }

    private fun buildChipsRecycler(){
        val timeLine:List<String> = arrayListOf("Last 7 days","Last 15 days","Last month","Last year")
        chipsAdapter= ChipsAdapter(timeLine,this)
        binding.rvTime.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.rvTime.adapter=chipsAdapter
    }
    private fun getBarEntries() {
        // creating a new array list

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntriesArrayList.add(BarEntry(1f, 4f))
        barEntriesArrayList.add(BarEntry(2f, 6f))
        barEntriesArrayList.add(BarEntry(3f, 8f))
        barEntriesArrayList.add(BarEntry(4f, 2f))
        barEntriesArrayList.add(BarEntry(5f, 4f))
        barEntriesArrayList.add(BarEntry(6f, 1f))

        val barDataSet = BarDataSet(barEntriesArrayList, "Geeks for Geeks")

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

    }
}