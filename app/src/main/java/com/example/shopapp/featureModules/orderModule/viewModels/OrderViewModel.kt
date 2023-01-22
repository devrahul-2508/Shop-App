package com.example.shopapp.featureModules.orderModule.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.shopapp.featureModules.authModule.repositories.AuthRepository
import com.example.shopapp.featureModules.orderModule.models.OrderModel
import com.example.shopapp.featureModules.orderModule.models.StatsModel
import com.example.shopapp.featureModules.orderModule.models.apiResponseModels.ApiResponseOrderModel
import com.example.shopapp.featureModules.orderModule.models.apiResponseModels.ApiResponseStatsModel
import com.example.shopapp.featureModules.orderModule.repositories.OrderRepository
import javax.inject.Inject

class OrderViewModel: ViewModel() {

    @Inject
    lateinit var orderRepository: OrderRepository

    private var errorData: MutableLiveData<Throwable> = MutableLiveData()

    fun placeOrder(orderModel: OrderModel): LiveData<ApiResponseOrderModel>{
        val successData: MutableLiveData<ApiResponseOrderModel> = MutableLiveData()
        orderRepository.placeOrder(orderModel,successData, errorData)
        return successData
    }

    fun fetchAllOrders(): LiveData<PagingData<OrderModel>>{
        return orderRepository.fetchAllOrders().cachedIn(viewModelScope)
    }

    fun fetchAdminOrders(): LiveData<PagingData<OrderModel>>{
        return orderRepository.fetchAdminOrders().cachedIn(viewModelScope)
    }

    fun fetchOrder(orderId: String): LiveData<ApiResponseOrderModel>{
        val successData: MutableLiveData<ApiResponseOrderModel> = MutableLiveData()
        orderRepository.getOrder(orderId,successData,errorData)
        return successData
    }

    fun updateAdminOrders(orderModel: OrderModel): LiveData<ApiResponseOrderModel>{
        val successData: MutableLiveData<ApiResponseOrderModel> = MutableLiveData()
        orderRepository.updateAdminOrders(orderModel,successData,errorData)
        return successData
    }
    fun fetchStats(timeline: String): LiveData<ApiResponseStatsModel>{
        val successData: MutableLiveData<ApiResponseStatsModel> = MutableLiveData()
        orderRepository.fetchStats(timeline,successData,errorData)
        return successData
    }


}