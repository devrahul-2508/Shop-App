package com.example.shopapp.featureModules.mainModule.interfaces

import com.example.shopapp.featureModules.mainModule.models.apiResponseModels.ApiResponseMainModels
import retrofit2.Call
import retrofit2.http.GET

interface MainRestApi {

     @GET("")
     fun fetchMainModels():Call<ApiResponseMainModels>
}