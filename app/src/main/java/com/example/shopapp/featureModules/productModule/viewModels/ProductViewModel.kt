package com.example.shopapp.featureModules.productModule.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.shopapp.featureModules.productModule.models.ProductModel
import com.example.shopapp.featureModules.productModule.models.apiResponseModels.ApiResponseProductModel
import com.example.shopapp.featureModules.productModule.repositories.ProductRepository
import javax.inject.Inject

class ProductViewModel: ViewModel() {

    @Inject
    lateinit var productRepository: ProductRepository

    private var errorData: MutableLiveData<Throwable> = MutableLiveData()

    fun getAllProducts(title: String?,category: String?): LiveData<PagingData<ProductModel>>{
        return productRepository.fetchAllProducts(title,category).cachedIn(viewModelScope)

    }
    fun getProductById(id: String): LiveData<ApiResponseProductModel>{
        val successData: MutableLiveData<ApiResponseProductModel> = MutableLiveData()
        productRepository.getProduct(id,successData, errorData)
        return successData
    }

    fun addProduct(title:String,desc:String,categories:String,size:String,color:String,price:String,imagePath:String):LiveData<ApiResponseProductModel>{
        val successData: MutableLiveData<ApiResponseProductModel> = MutableLiveData()
        productRepository.addNewProduct(title,desc,categories,size,color,price,imagePath,successData,errorData)
        return successData
    }
}