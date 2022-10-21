package com.example.shopapp.featureModules.cartModule.models.apiResponseModels

import com.example.shopapp.featureModules.authModule.models.UserModel
import com.example.shopapp.featureModules.cartModule.models.CartModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiResponseCartModel(
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
    val response: CartModel?=null
): java.io.Serializable