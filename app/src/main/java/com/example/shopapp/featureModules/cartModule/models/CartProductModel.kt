package com.example.shopapp.featureModules.cartModule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CartProductModel(
    @SerializedName("_id")
    @Expose
    var id: String?,
    @SerializedName("productId")
    @Expose
    var productId: String?,
    @SerializedName("title")
    @Expose
    var title: String?,
    @SerializedName("desc")
    @Expose
    var description: String?,
    @SerializedName("img")
    @Expose
    var img: String?,
    @SerializedName("price")
    @Expose
    var price: Int?,
    @SerializedName("quantity")
    @Expose
    var quantity: Int?,

    ) : java.io.Serializable