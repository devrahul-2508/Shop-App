package com.example.shopapp.featureModules.mainModule.viewModels

import com.example.shopapp.featureModules.mainModule.repositories.MainRepository
import javax.inject.Inject

class MainViewModel {

    @Inject
    lateinit var mainRepository: MainRepository
}