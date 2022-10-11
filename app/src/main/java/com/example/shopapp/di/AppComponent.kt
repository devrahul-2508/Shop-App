package com.example.shopapp.di

import com.example.shopapp.utility.Constants
import com.example.shopapp.utility.DataStoreManager
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

 @Named(Constants.Retrofit.WITHOUT_HEADERS)
 fun providesRetrofitWithoutHeaders(): Retrofit

 @Named(Constants.Retrofit.WITH_HEADERS)
 fun providesRetrofitWithHeaders(): Retrofit

 fun providesDataStoreManager(): DataStoreManager


}