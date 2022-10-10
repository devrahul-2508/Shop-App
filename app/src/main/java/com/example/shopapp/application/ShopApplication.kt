package com.example.shopapp.application

import android.app.Application
import com.example.shopapp.di.AppComponent
import com.example.shopapp.di.AppModule
import com.example.shopapp.di.DaggerAppComponent


class ShopApplication: Application() {

   private lateinit var applicationComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}