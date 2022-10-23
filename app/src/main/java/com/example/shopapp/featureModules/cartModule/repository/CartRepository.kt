package com.example.shopapp.featureModules.cartModule.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.shopapp.featureModules.cartModule.interfaces.CartRestApi
import com.example.shopapp.featureModules.cartModule.models.apiResponseModels.ApiResponseCartModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartRepository(
    private val cartRestApi: CartRestApi
) {
    fun getCart(
        data: MutableLiveData<ApiResponseCartModel>,
        error: MutableLiveData<Throwable>
    ){
        cartRestApi.getCart().enqueue(object : Callback<ApiResponseCartModel>{
            override fun onResponse(
                call: Call<ApiResponseCartModel>,
                response: Response<ApiResponseCartModel>
            ) {
                if (response.body()!=null){
                    data.value = response.body()
                    Log.d("BAM",response.toString())
                }
                else{
                    error.value = null
                }
            }

            override fun onFailure(call: Call<ApiResponseCartModel>, t: Throwable) {
               error.value = t
            }

        })
    }
}