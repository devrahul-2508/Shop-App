package com.example.shopapp.featureModules.authModule.interfaces

import com.example.shopapp.featureModules.authModule.models.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthRestApi {

    @POST("/api/auth/login")
    fun login(@Body userModel: UserModel): Call<UserModel>

    /*@POST("")
    fun register(@Body userModel: UserModel): Call<UserModel>

    @GET("")
    fun getUser(
        @Query("id") id: String
    ): Call<UserModel>

    @GET("")
    fun getAllUsers(): Call<List<UserModel>>*/
}
