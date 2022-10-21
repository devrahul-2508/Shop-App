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
    val products: List<CartProductModel>
): java.io.Serializable
