package com.example.shopapp.featureModules.authModule.interfaces

import com.example.shopapp.featureModules.authModule.models.UserModel
import com.example.shopapp.featureModules.authModule.models.apiResponseModels.ApiResponseUserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthRestApi {

    @POST("/api/auth/login")
    fun login(@Body userModel: UserModel): Call<ApiResponseUserModel>

    @POST("/api/auth/register")
    fun register(@Body userModel: UserModel): Call<ApiResponseUserModel>

    /*

    @GET("")
    fun getUser(
        @Query("id") id: String
    ): Call<UserModel>

    @GET("")
    fun getAllUsers(): Call<List<UserModel>>*/
}
