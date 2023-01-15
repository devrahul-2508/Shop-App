package com.example.shopapp.featureModules.orderModule.ui.activities

import android.media.MediaCodec.LinearBlock
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.LinearLayout.HORIZONTAL
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopapp.R
import com.example.shopapp.databinding.ActivityStatsBinding
import com.example.shopapp.featureModules.orderModule.ui.adapters.ChipsAdapter

class StatsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStatsBinding
    private lateinit var chipsAdapter: ChipsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_stats)

        buildChipsRecycler()
    }

    private fun buildChipsRecycler(){
        val timeLine:List<String> = arrayListOf("Last 7 days","Last 15 days","Last month","Last year")
        chipsAdapter= ChipsAdapter(timeLine,this)
        binding.rvTime.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.rvTime.adapter=chipsAdapter
    }
}