package com.example.shopapp.featureModules.cartModule.di

import com.example.shopapp.di.scopes.ApplicationScope
import com.example.shopapp.featureModules.cartModule.interfaces.CartRestApi
import com.example.shopapp.featureModules.cartModule.repository.CartRepository
import com.example.shopapp.utility.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class CartModule {

    @Provides
    @ApplicationScope
    @Named(Constants.REST_API_WITH_HEADERS)
    fun provideCartRestApiWithoutHeaders(
        @Named(Constants.Retrofit.WITHOUT_HEADERS) retrofit: Retrofit
    ):CartRestApi = retrofit.create(CartRestApi::class.java)

    @Provides
    @ApplicationScope
    @Named(Constants.REST_API_WITHOUT_HEADERS)
    fun provideCartRestApiWithHeaders(
        @Named(Constants.Retrofit.WITH_HEADERS) retrofit: Retrofit
    ): CartRestApi  = retrofit.create(CartRestApi::class.java)

    @Provides
    @ApplicationScope
    fun providesCartRepository(
        @Named(Constants.REST_API_WITH_HEADERS) cartRestApi: CartRestApi
    ):CartRepository = CartRepository(cartRestApi)
}