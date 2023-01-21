package com.example.shopapp.featureModules.orderModule.interfaces

import com.example.shopapp.featureModules.orderModule.models.OrderModel
import com.example.shopapp.featureModules.orderModule.models.StatsModel
import com.example.shopapp.featureModules.orderModule.models.apiResponseModels.ApiResponseOrderModel
import com.example.shopapp.featureModules.orderModule.models.apiResponseModels.ApiResponseOrderModels
import com.example.shopapp.featureModules.orderModule.models.apiResponseModels.ApiResponseStatsModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OrderRestApi {

    @POST("api/orders")
    fun placeOrder(@Body orderModel: OrderModel): Call<ApiResponseOrderModel>

    @GET("api/orders/find")
    suspend fun getOrders(
        @Query("page") page: Int
    ): ApiResponseOrderModels

    @GET("api/orders")
    fun getOrder(
        @Query("id") id: String
    ): Call<ApiResponseOrderModel>


    @GET("api/orders/income")
    fun getStats(
        @Query("timeline") timeLine: String
    ): Call<ApiResponseStatsModel>


    @GET("api/orders/all")
    suspend fun getAdminOrders(
        @Query("page") page: Int
    ):ApiResponseOrderModels

}