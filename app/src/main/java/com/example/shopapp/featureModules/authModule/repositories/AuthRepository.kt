package com.example.shopapp.featureModules.authModule.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.shopapp.featureModules.authModule.interfaces.AuthRestApi
import com.example.shopapp.featureModules.authModule.models.FcmTokenModel
import com.example.shopapp.featureModules.authModule.models.UserModel
import com.example.shopapp.featureModules.authModule.models.apiResponseModels.ApiResponseUserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository(
    private val aRestApi: AuthRestApi
) {

    fun loginUser(
        userModel: UserModel,
        data: MutableLiveData<ApiResponseUserModel>,
        error: MutableLiveData<Throwable>
    ){
        aRestApi.login(userModel).enqueue(object : Callback<ApiResponseUserModel>{

            override fun onResponse(call: Call<ApiResponseUserModel>, response: Response<ApiResponseUserModel>) {


               if (response.body()!=null){
                    data.value = response.body()
                }
                else{
                    error.value = null
                }
            }

            override fun onFailure(call: Call<ApiResponseUserModel>, t: Throwable) {
                error.value = t

            }

        })
    }

    fun registerUser(
        userModel: UserModel,
        data: MutableLiveData<ApiResponseUserModel>,
        error: MutableLiveData<Throwable>
    ){
        aRestApi.register(userModel).enqueue(object : Callback<ApiResponseUserModel>{

            override fun onResponse(call: Call<ApiResponseUserModel>, response: Response<ApiResponseUserModel>) {


                if (response.body()!=null){
                    data.value = response.body()
                }
                else{
                    error.value = null
                }
            }

            override fun onFailure(call: Call<ApiResponseUserModel>, t: Throwable) {
                error.value = t

            }

        })
    }

    fun setFcmToken(
        fcmTokenModel: FcmTokenModel,
        data: MutableLiveData<ApiResponseUserModel>,
        error: MutableLiveData<Throwable>
        ){
        aRestApi.setFcmToken(fcmTokenModel).enqueue(object : Callback<ApiResponseUserModel>{
            override fun onResponse(
                call: Call<ApiResponseUserModel>,
                response: Response<ApiResponseUserModel>
            ) {
                if (response.body()!=null){
                    data.value = response.body()
                }
                else{
                    error.value = null
                }
            }

            override fun onFailure(call: Call<ApiResponseUserModel>, t: Throwable) {
                error.value = t
            }

        })
    }
}