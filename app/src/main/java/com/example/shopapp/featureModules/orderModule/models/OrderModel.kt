package com.example.shopapp.featureModules.orderModule.models

import com.example.shopapp.featureModules.cartModule.models.CartProductModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OrderModel(

    @SerializedName("_id")
    @Expose
    val orderId: String?=null,
    @SerializedName("userId")
    @Expose
    val userId: String?=null,
    @SerializedName("products")
    @Expose
    val products: List<OrderProductModel>,
    @SerializedName("amount")
    @Expose
    val amount: Int?=null,
    @SerializedName("address")
    @Expose
    val address: String?=null,
    @SerializedName("status")
    @Expose
    val status: String?=null,
    @SerializedName("modeOfPayment")
    @Expose
    val modeOfPayment: String?=null,
    @SerializedName("createdAt")
    @Expose
    val createdAt: String?=null,
    @SerializedName("updatedAt")
    @Expose
    val updatedAt: String?=null


): java.io.Serializable
