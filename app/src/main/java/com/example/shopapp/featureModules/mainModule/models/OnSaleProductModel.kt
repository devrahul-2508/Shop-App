package com.example.shopapp.featureModules.mainModule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OnSaleProductModel(
    @SerializedName("products")
    @Expose
    var products: List<ProductModel>?=null
):java.io.Serializable