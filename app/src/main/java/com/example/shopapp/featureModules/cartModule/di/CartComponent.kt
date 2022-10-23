package com.example.shopapp.featureModules.cartModule.di

import com.example.shopapp.di.AppComponent
import com.example.shopapp.di.scopes.ApplicationScope
import com.example.shopapp.featureModules.cartModule.ui.fragments.CartFragment
import com.example.shopapp.featureModules.cartModule.viewModels.CartViewModel
import dagger.Component

@ApplicationScope
@Component(dependencies = [AppComponent::class], modules = [CartModule::class])
interface CartComponent {

    fun inject(cartFragment: CartFragment)
    fun inject(cartViewModel: CartViewModel)
}