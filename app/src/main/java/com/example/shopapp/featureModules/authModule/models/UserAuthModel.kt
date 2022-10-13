package com.example.shopapp.featureModules.authModule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserAuthModel(
    @SerializedName("_id")
    @Expose
    val id: String?=null,
    @SerializedName("username")
    @Expose
    val userName:String?=null,
    @SerializedName("email")
    @Expose
    val email: String?=null,
    @SerializedName("isAdmin")
    @Expose
    val isAdmin: Boolean?=false,
    @SerializedName("accessToken")
    @Expose
    val accessToken: String?=null,
    @SerializedName("createdAt")
    @Expose
    val createdAt:String?=null,
    @SerializedName("updatedAt")
    @Expose
    val updatedAt: String?=null
): Serializable