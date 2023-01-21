package com.example.shopapp.featureModules.orderModule.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.shopapp.featureModules.orderModule.interfaces.OrderRestApi
import com.example.shopapp.featureModules.orderModule.models.OrderModel
import com.example.shopapp.featureModules.orderModule.models.StatsModel
import com.example.shopapp.featureModules.orderModule.models.apiResponseModels.ApiResponseOrderModel
import com.example.shopapp.featureModules.orderModule.models.apiResponseModels.ApiResponseStatsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Objects

class OrderRepository(
    private val orderRestApi: OrderRestApi
) {

    fun placeOrder(
        orderModel: OrderModel,
        data: MutableLiveData<ApiResponseOrderModel>,
        error: MutableLiveData<Throwable>
    ){
        orderRestApi.placeOrder(orderModel).enqueue(object : Callback<ApiResponseOrderModel>{
            override fun onResponse(
                call: Call<ApiResponseOrderModel>,
                response: Response<ApiResponseOrderModel>
            ) {
                if (response.body()!=null){
                    data.value = response.body()
                }
                else{
                    error.value = null
                }
            }

            override fun onFailure(call: Call<ApiResponseOrderModel>, t: Throwable) {
               error.value = t
            }

        })
    }

    fun fetchAllOrders()=Pager(
        config = PagingConfig(pageSize = 4),
        pagingSourceFactory = {OrderPagingSource(orderRestApi)}
    ).liveData

    fun fetchAdminOrders()=Pager(
        config = PagingConfig(pageSize = 4),
        pagingSourceFactory = {AdminOrdersPagingSource(orderRestApi)}
    ).liveData

    fun getOrder(
        orderId: String,
        data: MutableLiveData<ApiResponseOrderModel>,
        error: MutableLiveData<Throwable>
    ){
        orderRestApi.getOrder(orderId).enqueue(object : Callback<ApiResponseOrderModel>{
            override fun onResponse(
                call: Call<ApiResponseOrderModel>,
                response: Response<ApiResponseOrderModel>
            ) {
                if (response.body()!=null){
                    data.value = response.body()
                }
                else{
                    error.value = null
                }
            }

            override fun onFailure(call: Call<ApiResponseOrderModel>, t: Throwable) {
                error.value = t

            }

        })
    }

    fun fetchStats(
        timeLine: String,
        data: MutableLiveData<ApiResponseStatsModel>,
        error: MutableLiveData<Throwable>
    ){
        orderRestApi.getStats(timeLine).enqueue(object : Callback<ApiResponseStatsModel>{
            override fun onResponse(call: Call<ApiResponseStatsModel>, response: Response<ApiResponseStatsModel>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<ApiResponseStatsModel>, t: Throwable) {
                error.value = t
            }

        })
    }

}