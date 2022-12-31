package com.example.shopapp.featureModules.productModule.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.shopapp.featureModules.productModule.interfaces.ProductRestApi
import com.example.shopapp.featureModules.productModule.di.models.apiResponseModels.ApiResponseProductModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository(private val productRestApi: ProductRestApi) {

    fun fetchAllProducts(
        title: String?,
        category: String?
    ) = Pager(
        pagingSourceFactory = { ProductPagingSource(productRestApi, title, category) },
        config = PagingConfig(pageSize = 4)
    ).liveData

    fun getProduct(
        id: String,
        data: MutableLiveData<ApiResponseProductModel>,
        error: MutableLiveData<Throwable>
    ){
        productRestApi.getProduct(id).enqueue(object: Callback<ApiResponseProductModel>{
            override fun onResponse(
                call: Call<ApiResponseProductModel>,
                response: Response<ApiResponseProductModel>
            ) {
                if (response.body()!=null){
                    data.value = response.body()
                }
                else{
                    error.value = null
                }
            }

            override fun onFailure(call: Call<ApiResponseProductModel>, t: Throwable) {
                error.value = t
            }

        })
    }
}