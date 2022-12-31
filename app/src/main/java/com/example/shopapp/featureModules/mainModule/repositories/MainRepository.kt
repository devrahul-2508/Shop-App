package com.example.shopapp.featureModules.mainModule.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.shopapp.featureModules.mainModule.interfaces.MainRestApi
import com.example.shopapp.featureModules.mainModule.models.apiResponseModels.ApiResponseMainModels
import com.example.shopapp.featureModules.productModule.repositories.ProductPagingSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository(private val mainRestApi: MainRestApi) {

    fun fetchMainModels(

    ) = Pager(
        pagingSourceFactory = { MainHomePagingSource(mainRestApi) },
        config = PagingConfig(pageSize = 4)
    ).liveData
        }

