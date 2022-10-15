package com.example.shopapp.featureModules.productModule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import  java.io.Serializable

data class ProductModel(

    @SerializedName("_id")
    @Expose
    var id: String?,
    @SerializedName("title")
    @Expose
    var title: String?,
    @SerializedName("categories")
    @Expose
    var categories: List<String?>?,
    @SerializedName("color")
    @Expose
    var color: String?,
    @SerializedName("desc")
    @Expose
    var desc: String?,
    @SerializedName("img")
    @Expose
    var img: String?,
    @SerializedName("price")
    @Expose
    var price: Int?,
    @SerializedName("size")
    @Expose
    var size: String?,
    @SerializedName("createdAt")
    @Expose
    var createdAt: String?,
    @SerializedName("updatedAt")
    @Expose
    var updatedAt: String?
): Serializable