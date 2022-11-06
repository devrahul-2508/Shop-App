package com.example.shopapp.featureModules.orderModule.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.shopapp.featureModules.orderModule.interfaces.OrderRestApi

class OrderRepository(
    private val orderRestApi: OrderRestApi
) {

    fun fetchAllOrders()=Pager(
        config = PagingConfig(pageSize = 4),
        pagingSourceFactory = {OrderPagingSource(orderRestApi)}
    ).liveData

}