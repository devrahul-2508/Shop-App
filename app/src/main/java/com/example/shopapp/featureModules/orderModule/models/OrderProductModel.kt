package com.example.shopapp.featureModules.orderModule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OrderProductModel(

    @SerializedName("_id")
    @Expose
    var id: String?=null,
    @SerializedName("productId")
    @Expose
    var productId: String?=null,
    @SerializedName("title")
    @Expose
    var title: String?=null,
    @SerializedName("desc")
    @Expose
    var description: String?=null,
    @SerializedName("img")
    @Expose
    var img: String?=null,
    @SerializedName("price")
    @Expose
    var price: Int?=null,
    @SerializedName("quantity")
    @Expose
    var quantity: Int?=null,

    ): java.io.Serializable
