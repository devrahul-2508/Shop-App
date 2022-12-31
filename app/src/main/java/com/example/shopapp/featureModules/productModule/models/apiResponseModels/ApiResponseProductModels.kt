package com.example.shopapp.featureModules.productModule.models.apiResponseModels

import android.provider.ContactsContract.Profile
import com.example.shopapp.featureModules.authModule.models.UserModel
import com.example.shopapp.featureModules.productModule.models.ProductModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import  java.io.Serializable


data class ApiResponseProductModels(
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
    val response: List<ProductModel>?= null
): Serializable