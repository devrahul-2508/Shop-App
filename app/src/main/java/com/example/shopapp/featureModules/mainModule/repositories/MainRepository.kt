package com.example.shopapp.featureModules.mainModule.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopapp.featureModules.mainModule.interfaces.MainRestApi
import com.example.shopapp.featureModules.mainModule.models.apiResponseModels.ApiResponseMainModels
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository(private val mainRestApi: MainRestApi) {

    private fun mainModels(
        data: MutableLiveData<ApiResponseMainModels>,
    error:MutableLiveData<Throwable>
    ){
        mainRestApi.fetchMainModels().enqueue(object :Callback<ApiResponseMainModels>{
            override fun onResponse(
                call: Call<ApiResponseMainModels>,
                response: Response<ApiResponseMainModels>
            ) {
               if (response.body()!=null){
                   data.value = response.body()
               }
                else{
                    error.value = null
               }
            }

            override fun onFailure(call: Call<ApiResponseMainModels>, t: Throwable) {
                error.value = t
            }

        })
    }
}