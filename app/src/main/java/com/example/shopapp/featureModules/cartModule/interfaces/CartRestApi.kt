package com.example.shopapp.featureModules.cartModule.interfaces

import com.example.shopapp.featureModules.cartModule.models.apiResponseModels.ApiResponseCartModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface CartRestApi {

@GET("api/carts/find")
fun getCart(): Call<ApiResponseCartModel>
}