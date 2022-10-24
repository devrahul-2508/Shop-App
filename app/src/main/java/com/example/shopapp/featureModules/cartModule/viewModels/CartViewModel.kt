package com.example.shopapp.featureModules.cartModule.viewModels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopapp.featureModules.cartModule.models.CartProductModel
import com.example.shopapp.featureModules.cartModule.models.apiResponseModels.ApiResponseCartModel
import com.example.shopapp.featureModules.cartModule.repository.CartRepository
import javax.inject.Inject

class CartViewModel: ViewModel() {

    @Inject
    lateinit var cartRepository: CartRepository

    private val errorData: MutableLiveData<Throwable> = MutableLiveData()

    fun getCart(): LiveData<ApiResponseCartModel>{
        val successData: MutableLiveData<ApiResponseCartModel> = MutableLiveData()
        cartRepository.getCart(successData,errorData)
        return successData
    }

    fun modifyCart(cartProductModel: CartProductModel): LiveData<ApiResponseCartModel>{
        val successData: MutableLiveData<ApiResponseCartModel> = MutableLiveData()
        cartRepository.modifyCart(cartProductModel,successData,errorData)
        return successData
    }
}