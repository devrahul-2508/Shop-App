package com.example.shopapp.featureModules.orderModule.di

import com.example.shopapp.di.AppComponent
import com.example.shopapp.di.scopes.ApplicationScope
import com.example.shopapp.featureModules.cartModule.ui.fragments.CartFragment
import com.example.shopapp.featureModules.orderModule.ui.fragments.OrderFragment
import com.example.shopapp.featureModules.orderModule.viewModels.OrderViewModel
import dagger.Component
import javax.inject.Singleton

@ApplicationScope
@Component(dependencies = [AppComponent::class], modules = [OrderModule::class])
interface OrderComponent {

    fun inject(orderFragment: OrderFragment)
    fun inject(orderViewModel: OrderViewModel)
    fun inject(cartFragment: CartFragment)
}