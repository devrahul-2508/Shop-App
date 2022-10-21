package com.example.shopapp.featureModules.cartModule.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopapp.featureModules.cartModule.models.apiResponseModels.ApiResponseCartModel
import com.example.shopapp.featureModules.cartModule.repository.CartRepository
import javax.inject.Inject

class CartViewModel {

    @Inject
    lateinit var cartRepository: CartRepository

    val errorData: MutableLiveData<Throwable> = MutableLiveData()

    fun getCart(): LiveData<ApiResponseCartModel>{
        val successData: MutableLiveData<ApiResponseCartModel> = MutableLiveData()
        cartRepository.getCart(successData,errorData)
        return successData
    }
}