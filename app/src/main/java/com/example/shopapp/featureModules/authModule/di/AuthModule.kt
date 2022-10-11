package com.example.shopapp.featureModules.authModule.di

import com.example.shopapp.di.scopes.ApplicationScope
import com.example.shopapp.featureModules.authModule.interfaces.AuthRestApi
import com.example.shopapp.featureModules.authModule.repositories.AuthRepository
import com.example.shopapp.utility.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class AuthModule {

    @Provides
    @ApplicationScope
    @Named(Constants.REST_API_WITH_HEADERS)
    fun provideAuthRestApiWithHeaders(@Named(Constants.Retrofit.WITH_HEADERS) retrofit: Retrofit): AuthRestApi = retrofit.create(AuthRestApi::class.java)

    @Provides
    @ApplicationScope
    @Named(Constants.REST_API_WITHOUT_HEADERS)
    fun provideAuthRestApiWithoutHeaders(@Named(Constants.Retrofit.WITHOUT_HEADERS) retrofit: Retrofit): AuthRestApi = retrofit.create(AuthRestApi::class.java)

    @Provides
    @ApplicationScope
    fun providesAuthRepository(
       @Named(Constants.REST_API_WITH_HEADERS) authRestApi: AuthRestApi
    ):AuthRepository = AuthRepository(authRestApi)

}