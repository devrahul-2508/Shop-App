package com.example.shopapp.featureModules.productModule.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.applex.utsavadmin.utility.utilManager.RealPathUtil
import com.example.shopapp.featureModules.productModule.interfaces.ProductRestApi
import com.example.shopapp.featureModules.productModule.models.apiResponseModels.ApiResponseProductModel
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Multipart
import java.io.File

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

    fun addNewProduct(
        title:String,
        desc:String,
        categories:String,
        size:String,
        color:String,
        price:String,
        imagePath: String,
        data: MutableLiveData<ApiResponseProductModel>,
        error: MutableLiveData<Throwable>
    ){
        val titleBody = RequestBody.create(MediaType.parse("multipart/form-data"),title)
        val descBody = RequestBody.create(MediaType.parse("multipart/form-data"),desc)
        val categoriesBody = RequestBody.create(MediaType.parse("multipart/form-data"),categories)
        val sizeBody = RequestBody.create(MediaType.parse("multipart/form-data"),size)
        val colourBody =  RequestBody.create(MediaType.parse("multipart/form-data"),color)
        val priceBody = RequestBody.create(MediaType.parse("multipart/form-data"),price)

        val file = File(imagePath)
        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),file)
        val productImage = MultipartBody.Part.createFormData("img",file.name,requestFile)

        productRestApi.addNewProduct(titleBody,descBody,categoriesBody,sizeBody,colourBody,priceBody,productImage).enqueue(object : Callback<ApiResponseProductModel>{
            override fun onResponse(
                call: Call<ApiResponseProductModel>,
                response: Response<ApiResponseProductModel>
            ) {
                if (response.body()!=null){
                    data.value = response.body()
                    Log.d("BAM",response.body().toString())
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