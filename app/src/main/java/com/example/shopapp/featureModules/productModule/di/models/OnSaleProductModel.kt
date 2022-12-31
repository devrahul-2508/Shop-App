package com.example.shopapp.featureModules.productModule.di.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OnSaleProductModel(
    @SerializedName("products")
    @Expose
    var products: List<ProductModel>?=null
):java.io.Serializable