package com.example.shopapp.featureModules.orderModule.models.apiResponseModels

import com.example.shopapp.featureModules.orderModule.models.OrderModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiResponseOrderModels(
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
    val response: List<OrderModel>?=null
): java.io.Serializable
