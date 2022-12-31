package com.example.shopapp.featureModules.mainModule.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.shopapp.featureModules.mainModule.interfaces.MainRestApi
import com.example.shopapp.featureModules.mainModule.models.MainModels

class MainHomePagingSource(
    private val mainRestApi: MainRestApi
):PagingSource<Int,MainModels>() {
    override fun getRefreshKey(state: PagingState<Int, MainModels>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MainModels> {
        return try{
            val page = params.key ?: 1
            val response = mainRestApi.fetchMainModels(page)
            LoadResult.Page(
                data = response.response,
                prevKey = null,
                nextKey = if (response.response.isEmpty()) null else page+1
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }

    }
}