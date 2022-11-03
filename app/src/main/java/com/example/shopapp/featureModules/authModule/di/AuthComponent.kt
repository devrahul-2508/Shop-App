package com.example.shopapp.featureModules.authModule.di

import com.example.shopapp.di.AppComponent
import com.example.shopapp.di.scopes.ApplicationScope
import com.example.shopapp.featureModules.authModule.ui.activities.LoginActivity
import com.example.shopapp.featureModules.authModule.ui.activities.SignUpActivity
import com.example.shopapp.featureModules.authModule.ui.fragments.ProfileFragment
import com.example.shopapp.featureModules.authModule.viewmodels.AuthViewModel
import dagger.Component
import javax.inject.Singleton

@ApplicationScope
@Component(dependencies = [AppComponent::class], modules = [AuthModule::class])
interface AuthComponent {
    fun inject(loginActivity: LoginActivity)
    fun inject(authViewModel: AuthViewModel)
    fun inject(signUpActivity: SignUpActivity)
    fun inject(profileFragment: ProfileFragment)
}