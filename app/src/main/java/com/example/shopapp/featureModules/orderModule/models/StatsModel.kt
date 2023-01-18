package com.example.shopapp.featureModules.orderModule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StatsModel(
    @SerializedName("_id")
    @Expose
    val id: Float?=null,
    @SerializedName("total")
    @Expose
    val total: Float?=null

): java.io.Serializable
