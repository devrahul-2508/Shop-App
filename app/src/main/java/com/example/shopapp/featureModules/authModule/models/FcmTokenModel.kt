package com.example.shopapp.featureModules.authModule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FcmTokenModel(

    @SerializedName("userId")
    @Expose
    val userId: String?=null,
    @SerializedName("fcmToken")
    @Expose
    val fcmToken:String?=null,


): java.io.Serializable
