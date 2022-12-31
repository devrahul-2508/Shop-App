package com.example.shopapp.featureModules.mainModule.interfaces

import com.example.shopapp.featureModules.mainModule.models.apiResponseModels.ApiResponseMainModels
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MainRestApi {

     @GET("api/mainmodels")
     suspend fun fetchMainModels(
          @Query("page") page:Int
     ):ApiResponseMainModels
}