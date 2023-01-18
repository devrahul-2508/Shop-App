package com.example.shopapp.featureModules.orderModule.models.apiResponseModels

import com.example.shopapp.featureModules.orderModule.models.OrderModel
import com.example.shopapp.featureModules.orderModule.models.StatsModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiResponseStatsModel(
    @SerializedName("success")
    @Expose
    val success: Boolean,
    @SerializedName("code")
    @Expose
    val code: Int,
    @SerializedName("message")
    @Expose
    val message:String?=null,
    @SerializedName("response")
    @Expose
    val response: List<StatsModel>?=null
): java.io.Serializable
