package com.example.shopapp.featureModules.mainModule.models

import com.example.shopapp.featureModules.productModule.models.ProductModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TrendingProductModel(
    @SerializedName("products")
    @Expose
    var products: List<ProductModel>?=null
):java.io.Serializable