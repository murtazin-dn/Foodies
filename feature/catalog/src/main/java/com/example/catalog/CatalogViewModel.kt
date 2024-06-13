package com.example.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Result
import com.example.designsystem.state.EventHandler
import com.example.domain.usecase.AddToCartUseCase
import com.example.domain.usecase.GetCatalogUseCase
import com.example.domain.usecase.ReloadCategoriesUseCase
import com.example.domain.usecase.ReloadProductsUseCase
import com.example.domain.usecase.ReloadTagsUseCase
import com.example.domain.usecase.RemoveFromCartUseCase
import com.example.model.CatalogModel
import com.example.model.CategoryModel
import com.example.model.FilterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class CatalogViewModel @Inject constructor(
    private val getCatalogUseCase: GetCatalogUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val reloadProductsUseCase: ReloadProductsUseCase,
    private val reloadCategoriesUseCase: ReloadCategoriesUseCase,
    private val reloadTagsUseCase: ReloadTagsUseCase
) : ViewModel(), EventHandler<CatalogEvents> {

    val searchState = MutableStateFlow<String?>(null)
    val filterState = MutableStateFlow<List<FilterModel>>(listOf())
    val state: StateFlow<CatalogUIState> =
        combine(
            getCatalogUseCase.execute(),
            searchState,
            filterState
        ) { _catalog, _search, _filter ->
            when (_catalog) {
                is Result.Error -> return@combine CatalogUIState.Error(_catalog.message)
                is Result.Success -> {
                    val filterIds = _filter.mapNotNull {
                        if (it.isSelected) it.id else null
                    }

                    if (_search != null) {
                        val searchedCatalog = _catalog.value.copy(
                            products = _catalog.value.products.filter {
                                it.name.contains(_search, true)
                            })
                        return@combine if (searchedCatalog.products.isEmpty()) {
                            CatalogUIState.Success.EmptySearch(searchedCatalog)
                        } else if (_search == "") {
                            CatalogUIState.Success.EmptySearchValue(searchedCatalog)
                        } else {
                            CatalogUIState.Success.Data(searchedCatalog)
                        }
                    }

                    if (_filter.isEmpty()) {
                        filterState.value = _catalog.value.tags.map { tag ->
                            FilterModel(id = tag.id, name = tag.name)
                        }
                        CatalogUIState.Success.Data(_catalog.value)
                    } else {
                        if (filterIds.isEmpty()) {
                            CatalogUIState.Success.Data(_catalog.value)
                        } else {
                            val filteredCatalog = _catalog.value.copy(
                                products = _catalog.value.products.filter { product ->
                                    product.tags.any { it in filterIds }
                                })
                            if (filteredCatalog.products.isEmpty()) {
                                CatalogUIState.Success.EmptyFilter(filteredCatalog)
                            } else CatalogUIState.Success.Data(filteredCatalog)
                        }
                    }

                }
            }
        }
            .onStart { emit(CatalogUIState.Loading) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CatalogUIState.Loading
            )

    private fun addToCart(id: Int) {
        viewModelScope.launch { addToCartUseCase.execute(id) }
    }

    private fun removeFromCart(id: Int) {
        viewModelScope.launch { removeFromCartUseCase.execute(id) }
    }

    private fun search(searchValue: String?) {
        searchState.value = searchValue
    }

    private fun filter(filterValue: List<FilterModel>) {
        filterState.value = filterValue
    }

    private fun reload(){
        viewModelScope.launch { reloadProductsUseCase.execute() }
        viewModelScope.launch { reloadTagsUseCase.execute() }
        viewModelScope.launch { reloadCategoriesUseCase.execute() }
    }

    override fun obtainEvent(event: CatalogEvents) {
        when (event) {
            is CatalogEvents.AddToCart -> addToCart(event.id)
            is CatalogEvents.RemoveFromCartCart -> removeFromCart(event.id)
            is CatalogEvents.Filter -> filter(event.filter)
            is CatalogEvents.Search -> search(event.value)
            CatalogEvents.Reload -> reload()
        }
    }

}