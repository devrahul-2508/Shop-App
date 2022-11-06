package com.example.shopapp.featureModules.orderModule.interfaces

import com.example.shopapp.featureModules.orderModule.models.apiResponseModels.ApiResponseOrderModels
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OrderRestApi {

    @GET("api/orders/find")
    suspend fun getOrders(
        @Query("page") page: Int
    ): ApiResponseOrderModels

}