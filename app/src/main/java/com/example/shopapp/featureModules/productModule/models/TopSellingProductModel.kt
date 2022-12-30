package com.example.shopapp.featureModules.productModule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TopSellingProductModel(
    @SerializedName("products")
    @Expose
    var products: List<ProductModel>?=null
):java.io.Serializable
