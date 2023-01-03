package com.example.shopapp.di

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.shopapp.utility.Constants
import com.example.shopapp.utility.DataStoreManager
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class AppModule(val context: Application) {

    @Provides
    @Singleton
    @Named(Constants.APPLICATION_CONTEXT)
    fun provideContext(): Context = context

    @Provides
    fun providesOkHttpClient(dataStoreManager: DataStoreManager): OkHttpClient{
        val accessToken = runBlocking { dataStoreManager.accessToken.first() }
        Log.d("INTERCEPTOR",accessToken)
        return OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
            chain.proceed(newRequest)
        }.build()
    }

    @Provides
    @Named(Constants.Retrofit.WITH_HEADERS)
    fun providesRetrofitWithHeaders(client : OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Named(Constants.Retrofit.WITHOUT_HEADERS)
    fun providesRetrofitWithoutHeaders(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesDataStoreManager(@Named(Constants.APPLICATION_CONTEXT) context: Context): DataStoreManager = DataStoreManager(context)





}