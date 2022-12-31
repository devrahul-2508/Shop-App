package com.example.shopapp.featureModules.productModule.interfaces

import com.example.shopapp.featureModules.productModule.models.apiResponseModels.ApiResponseProductModel
import com.example.shopapp.featureModules.productModule.models.apiResponseModels.ApiResponseProductModels
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductRestApi{

    @GET("api/products")
    suspend fun getAllProducts(
        @Query("title") title: String?,
        @Query("category") category: String?,
        @Query("page") page: Int?
    ): ApiResponseProductModels

    @GET("api/products/find")
    fun getProduct(
        @Query("id") id: String
    ): Call<ApiResponseProductModel>
}