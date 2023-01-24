package com.example.shopapp.featureModules.authModule.interfaces

import com.example.shopapp.featureModules.authModule.models.FcmTokenModel
import com.example.shopapp.featureModules.authModule.models.UserModel
import com.example.shopapp.featureModules.authModule.models.apiResponseModels.ApiResponseUserModel
import retrofit2.Call
import retrofit2.http.*

interface AuthRestApi {

    @POST("/api/auth/login")
    fun login(@Body userModel: UserModel): Call<ApiResponseUserModel>

    @POST("/api/auth/register")
    fun register(@Body userModel: UserModel): Call<ApiResponseUserModel>

    @PUT("/api/auth/fcmtoken")
    fun setFcmToken(@Body fcmTokenModel: FcmTokenModel):Call<ApiResponseUserModel>

    /*

    @GET("")
    fun getUser(
        @Query("id") id: String
    ): Call<UserModel>

    @GET("")
    fun getAllUsers(): Call<List<UserModel>>*/
}
