package com.example.shopapp.featureModules.authModule.models.apiResponseModels

import com.example.shopapp.featureModules.authModule.models.UserAuthModel
import com.example.shopapp.featureModules.authModule.models.UserModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ApiResponseUserModel(
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
    val response: UserModel?=null
): Serializable