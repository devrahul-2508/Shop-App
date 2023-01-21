package com.example.shopapp.featureModules.orderModule.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.shopapp.featureModules.orderModule.interfaces.OrderRestApi
import com.example.shopapp.featureModules.orderModule.models.OrderModel

class AdminOrdersPagingSource(
    private val orderRestApi: OrderRestApi
):PagingSource<Int,OrderModel>() {
    override fun getRefreshKey(state: PagingState<Int, OrderModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, OrderModel> {
        return try {
            val page = params.key ?: 1
            val response = orderRestApi.getAdminOrders(page)
            LoadResult.Page(
                data = response.response!!,
                prevKey = null,
                nextKey = if (response.response.isEmpty()) null else page + 1
            )
        }catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}