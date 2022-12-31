package com.example.shopapp.featureModules.mainModule.di

import com.example.shopapp.di.scopes.ApplicationScope
import com.example.shopapp.featureModules.mainModule.interfaces.MainRestApi
import com.example.shopapp.featureModules.mainModule.repositories.MainRepository
import com.example.shopapp.utility.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class MainModule {


    @Provides
    @ApplicationScope
    @Named(Constants.REST_API_WITH_HEADERS)
    fun providesMainRestApiWithHeaders(@Named(Constants.Retrofit.WITH_HEADERS) retrofit: Retrofit):MainRestApi = retrofit.create(MainRestApi::class.java)

    @Provides
    @ApplicationScope
    @Named(Constants.REST_API_WITHOUT_HEADERS)
    fun providesMainRestApiWithoutHeaders(@Named(Constants.Retrofit.WITHOUT_HEADERS) retrofit: Retrofit):MainRestApi = retrofit.create(MainRestApi::class.java)

    @Provides
    @ApplicationScope
    fun providesMainRepository(@Named(Constants.REST_API_WITH_HEADERS) mainRestApi: MainRestApi):MainRepository = MainRepository(mainRestApi)
}