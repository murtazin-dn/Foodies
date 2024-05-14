package com.example.catalog.di

import com.example.catalog.CatalogViewModel
import com.example.domain.di.DomainComponent
import com.example.domain.usecase.GetCatalogUseCase
import dagger.Component

@Component(
    modules = [CatalogModule::class],
    dependencies = [CatalogFeatureDependencies::class]
)
@CatalogScope
internal interface CatalogComponent {
    @Component.Builder
    interface Builder {
        fun catalogFeatureDependencies(catalogFeatureDependencies: CatalogFeatureDependencies): Builder
        fun build(): CatalogComponent
    }

    fun getViewModel() : CatalogViewModel

}