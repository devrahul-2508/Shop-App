package com.example.shopapp.featureModules.productModule.interfaces

import com.example.shopapp.featureModules.productModule.models.apiResponseModels.ApiResponseProductModel
import com.example.shopapp.featureModules.productModule.models.apiResponseModels.ApiResponseProductModels
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
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

    @Multipart
    @POST("api/products")
    fun addNewProduct(
        @Part("title") title: RequestBody,
        @Part("desc") desc: RequestBody,
        @Part("categories") categories: RequestBody,
        @Part("size") size: RequestBody,
        @Part("color") color: RequestBody,
        @Part("price") price: RequestBody,
        @Part image:MultipartBody.Part
    ): Call<ApiResponseProductModel>
}