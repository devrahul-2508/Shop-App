package com.example.shopapp.featureModules.orderModule.di

import androidx.navigation.Navigator
import com.example.shopapp.di.scopes.ApplicationScope
import com.example.shopapp.featureModules.orderModule.interfaces.OrderRestApi
import com.example.shopapp.featureModules.orderModule.repositories.OrderRepository
import com.example.shopapp.utility.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named


@Module
class OrderModule {

    @Provides
    @ApplicationScope
    @Named(Constants.REST_API_WITH_HEADERS)
    fun provideOrderRestApiWithHeaders(@Named(Constants.Retrofit.WITH_HEADERS) retrofit: Retrofit) = retrofit.create(OrderRestApi::class.java)


    @Provides
    @ApplicationScope
    @Named(Constants.REST_API_WITHOUT_HEADERS)
    fun provideOrderRestApiWithoutHeaders(@Named(Constants.Retrofit.WITHOUT_HEADERS) retrofit: Retrofit) = retrofit.create(OrderRestApi::class.java)

    @Provides
    @ApplicationScope
    fun providesOrderRepository(
       @Named(Constants.REST_API_WITH_HEADERS) orderRestApi: OrderRestApi
    ): OrderRepository = OrderRepository(orderRestApi)



}