package com.example.shopapp.featureModules.productModule.models.apiResponseModels

import com.example.shopapp.featureModules.productModule.models.ProductModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ApiResponseProductModel(
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
    val response: ProductModel?= null
): Serializable
