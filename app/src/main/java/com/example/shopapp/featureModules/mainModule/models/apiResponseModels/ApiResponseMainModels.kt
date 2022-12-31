package com.example.shopapp.featureModules.mainModule.models.apiResponseModels

import com.example.shopapp.featureModules.mainModule.models.MainModels
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiResponseMainModels(
    @SerializedName("success")
    @Expose
    var success:String?=null,
    @SerializedName("code")
    @Expose
    var code:String?=null,
    @SerializedName("message")
    @Expose
    var message:String?=null,
    @SerializedName("response")
    @Expose
    var response:List<MainModels>
):java.io.Serializable