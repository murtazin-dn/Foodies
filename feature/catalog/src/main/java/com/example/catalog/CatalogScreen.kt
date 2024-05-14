package com.example.catalog

import android.widget.Button
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
internal fun CatalogRoute(
    viewModel: CatalogViewModel
){
    val state by viewModel.state.collectAsState()
    CatalogScreen(
        state,
        {viewModel.addToCart(it)},
        {viewModel.removeFromCart(it)}
    )
}

@Composable
internal fun CatalogScreen(
    state: CatalogUIState,
    onAddToCartClick: (Int) -> Unit,
    onRemoveFromCartClick: (Int) -> Unit
) {
    when (state){
        is CatalogUIState.Error -> CatalogError()
        CatalogUIState.Loading -> CatalogLoading()
        is CatalogUIState.Success -> CatalogLoadedData(
            state = state,
            onAddToCartClick = onAddToCartClick,
            onRemoveFromCartClick = onRemoveFromCartClick
        )
    }
}

@Composable
internal fun CatalogLoading(){
    CircularProgressIndicator()
}
@Composable
internal fun CatalogError(){
}
@Composable
internal fun CatalogLoadedData(
    state: CatalogUIState.Success,
    onAddToCartClick: (Int) -> Unit,
    onRemoveFromCartClick: (Int) -> Unit
){
    LazyColumn {
        itemsIndexed(state.data){ _, item ->
            Column {
                item.products.forEach { product ->
                    Column {
                        Text(
                            text = product.name
                        )
                    }
                    Row {
                        Text(
                            modifier = Modifier.clickable { onRemoveFromCartClick.invoke(product.id) },
                            text = "remove"
                        )
                        Text(
                            text = product.countInCart.toString()
                        )
                        Text(
                            modifier = Modifier.clickable { onAddToCartClick.invoke(product.id) },
                            text = "add"
                        )
                    }
                }
            }
        }
    }
}
