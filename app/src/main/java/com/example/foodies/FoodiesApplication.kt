package com.example.foodies

import android.app.Application
import com.example.cart.di.CartComponent
import com.example.cart.di.CartFeatureDependenciesStore
import com.example.cart.di.DaggerCartComponent
import com.example.catalog.di.CatalogFeatureDependenciesStore
import com.example.data.di.DaggerDataComponent
import com.example.data.di.DataComponent
import com.example.domain.di.DaggerDomainComponent
import com.example.domain.di.DomainComponent
import com.example.foodies.di.AppComponent
import com.example.foodies.di.DaggerAppComponent
import com.example.network.di.DaggerNetworkComponent
import com.example.network.di.NetworkComponent
import com.example.productdetail.di.ProductDetailFeatureDependenciesStore

class FoodiesApplication: Application() {

    private val cartComponent: CartComponent by lazy {
        DaggerCartComponent.builder().build()
    }
    private val networkComponent: NetworkComponent by lazy {
        DaggerNetworkComponent.builder().build()
    }
    private val dataComponent: DataComponent by lazy {
        DaggerDataComponent
            .builder()
            .networkComponent(networkComponent)
            .build()
    }
    private val domainComponent: DomainComponent by lazy {
        DaggerDomainComponent
            .builder()
            .dataComponent(dataComponent)
            .cartComponent(cartComponent)
            .build()
    }
    override fun onCreate() {
        super.onCreate()
        val appComponent = DaggerAppComponent.builder().domainComponent(domainComponent).build()
        CatalogFeatureDependenciesStore.deps = appComponent
        ProductDetailFeatureDependenciesStore.deps = appComponent
        CartFeatureDependenciesStore.deps = appComponent
    }


}