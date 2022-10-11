package com.example.shopapp.featureModules.authModule.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.shopapp.featureModules.authModule.interfaces.AuthRestApi
import com.example.shopapp.featureModules.authModule.models.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository(
    private val aRestApi: AuthRestApi
) {

    fun loginUser(
        userModel: UserModel,
        data: MutableLiveData<UserModel>,
        error: MutableLiveData<Throwable>
    ){
        aRestApi.login(userModel).enqueue(object : Callback<UserModel>{

            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                Log.d("BAM","Login Hiited")
                if (response.body()!=null){
                    data.value = response.body()
                    Log.d("BAM",response.body().toString())
                }
                else{
                    error.value = null
                }
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                error.value = t
            }

        })
    }
}