package com.example.shopapp.featureModules.productModule.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.shopapp.featureModules.productModule.interfaces.ProductRestApi
import com.example.shopapp.featureModules.productModule.models.ProductModel

class ProductPagingSource(
    private val productRestApi: ProductRestApi,
    private val title: String?,
    private val category: String?,
):PagingSource<Int, ProductModel>(){
    override fun getRefreshKey(state: PagingState<Int, ProductModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductModel> {
        return try {
            val page = params.key ?: 1
            val response = productRestApi.getAllProducts(title,category,page)
            LoadResult.Page(
                data = response.response!!,
                prevKey = null,
                nextKey = if (response.response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    }
