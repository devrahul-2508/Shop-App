package com.example.shopapp.featureModules.authModule.viewmodels

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopapp.featureModules.authModule.models.UserModel
import com.example.shopapp.featureModules.authModule.models.apiResponseModels.ApiResponseUserModel
import com.example.shopapp.featureModules.authModule.repositories.AuthRepository
import javax.inject.Inject

class AuthViewModel: ViewModel() {

    @Inject
    lateinit var authRepository: AuthRepository

    private val errorData = MutableLiveData<Throwable>()

    fun loginUser(userModel: UserModel): LiveData<ApiResponseUserModel>{
        val successData : MutableLiveData<ApiResponseUserModel> = MutableLiveData()
        authRepository.loginUser(userModel,successData, errorData)
        return successData
    }
}