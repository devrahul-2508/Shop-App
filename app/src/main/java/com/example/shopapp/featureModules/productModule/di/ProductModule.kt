package com.example.shopapp.featureModules.productModule.di

import com.example.shopapp.di.scopes.ApplicationScope
import com.example.shopapp.featureModules.productModule.interfaces.ProductRestApi
import com.example.shopapp.featureModules.productModule.repositories.ProductRepository
import com.example.shopapp.utility.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class ProductModule {

    @Provides
    @ApplicationScope
    @Named(Constants.REST_API_WITH_HEADERS)
    fun provideProductRestApiWithHeaders(@Named(Constants.Retrofit.WITH_HEADERS) retrofit: Retrofit): ProductRestApi = retrofit.create(
        ProductRestApi::class.java)

    @Provides
    @ApplicationScope
    @Named(Constants.REST_API_WITHOUT_HEADERS)
    fun provideProductRestApiWithoutHeaders(@Named(Constants.Retrofit.WITHOUT_HEADERS) retrofit: Retrofit): ProductRestApi = retrofit.create(
        ProductRestApi::class.java)

    @Provides
    @ApplicationScope
    fun providesProductRepository(
        @Named(Constants.REST_API_WITH_HEADERS) productRestApi: ProductRestApi
    ): ProductRepository = ProductRepository(productRestApi)
}