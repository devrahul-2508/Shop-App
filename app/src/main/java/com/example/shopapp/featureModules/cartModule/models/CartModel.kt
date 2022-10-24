package com.example.shopapp.featureModules.cartModule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CartModel(
    @SerializedName("_id")
    @Expose
    val id: String,
    @SerializedName("userId")
    @Expose
    val userId: String,
    @SerializedName("products")
    @Expose
    val products: List<CartProductModel>,
    @SerializedName("bill")
    @Expose
    val bill: Int?=null,
    @SerializedName("createdAt")
    @Expose
    val createdAt: String?=null,
    @SerializedName("updatedAt")
    @Expose
    val updatedAt: String?=null
): java.io.Serializable
