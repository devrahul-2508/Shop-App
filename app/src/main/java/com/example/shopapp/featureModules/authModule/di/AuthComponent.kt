package com.example.shopapp.featureModules.authModule.di

import com.example.shopapp.di.AppComponent
import com.example.shopapp.di.scopes.ApplicationScope
import com.example.shopapp.featureModules.authModule.ui.activities.LoginActivity
import dagger.Component
import javax.inject.Singleton

@ApplicationScope
@Component(dependencies = [AppComponent::class], modules = [AuthModule::class])
interface AuthComponent {
    fun inject(loginActivity: LoginActivity)
}