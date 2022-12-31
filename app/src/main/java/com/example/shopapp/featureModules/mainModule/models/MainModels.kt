package com.example.shopapp.featureModules.mainModule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MainModels(
    @SerializedName("viewType")
    @Expose
    var viewType:String?=null,
    @SerializedName("viewName")
    @Expose
    var viewName:String?=null,
    @SerializedName("topSellingProductModel")
    @Expose
    var topSellingProductModel: TopSellingProductModel?=null,
    @SerializedName("trendingProductModel")
    @Expose
    var trendingProductModel: TrendingProductModel?=null,
    @SerializedName("onSaleProductModel")
    @Expose
    var onSaleProductModel: OnSaleProductModel
)
