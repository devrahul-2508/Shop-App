package com.example.shopapp.featureModules.mainModule.di

import com.example.shopapp.databinding.FragmentHomeBinding
import com.example.shopapp.di.AppComponent
import com.example.shopapp.di.AppModule
import com.example.shopapp.di.scopes.ApplicationScope
import com.example.shopapp.featureModules.mainModule.ui.fragments.HomeFragment
import com.example.shopapp.featureModules.mainModule.viewModels.MainViewModel
import dagger.Component

@ApplicationScope
@Component(modules = [MainModule::class], dependencies = [AppComponent::class])
interface MainComponent {
    fun inject(mainViewModel: MainViewModel)
    fun inject(homeFragment: HomeFragment)
}