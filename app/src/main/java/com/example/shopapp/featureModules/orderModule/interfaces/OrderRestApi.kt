package com.example.shopapp.featureModules.orderModule.interfaces

import com.example.shopapp.featureModules.orderModule.models.OrderModel
import com.example.shopapp.featureModules.orderModule.models.apiResponseModels.ApiResponseOrderModel
import com.example.shopapp.featureModules.orderModule.models.apiResponseModels.ApiResponseOrderModels
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

}