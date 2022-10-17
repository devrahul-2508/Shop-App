package com.example.shopapp.featureModules.productModule.di

import com.example.shopapp.di.AppComponent
import com.example.shopapp.di.scopes.ApplicationScope
import com.example.shopapp.featureModules.productModule.ui.activities.ProductDetailsActivity
import com.example.shopapp.featureModules.productModule.ui.fragments.HomeFragment
import com.example.shopapp.featureModules.productModule.viewModels.ProductViewModel
import dagger.Component

@ApplicationScope
@Component(dependencies = [AppComponent::class], modules = [ProductModule::class])
interface ProductComponent {

    fun inject(productViewModel: ProductViewModel)
    fun inject(homeFragment: HomeFragment)
    fun inject(productDetailsActivity: ProductDetailsActivity)
}