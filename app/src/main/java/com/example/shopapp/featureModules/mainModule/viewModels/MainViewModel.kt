package com.example.shopapp.featureModules.mainModule.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.shopapp.featureModules.mainModule.models.MainModels
import com.example.shopapp.featureModules.mainModule.models.apiResponseModels.ApiResponseMainModels
import com.example.shopapp.featureModules.mainModule.repositories.MainRepository
import javax.inject.Inject

class MainViewModel:ViewModel() {

    @Inject
    lateinit var mainRepository: MainRepository

    private var errorData: MutableLiveData<Throwable> = MutableLiveData()

   fun mainModels():LiveData<PagingData<MainModels>>{
      return mainRepository.fetchMainModels().cachedIn(viewModelScope)
    }
}