package com.example.catalog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.designsystem.component.topbar.CatalogTopBar
import com.example.designsystem.parameterprovider.CatalogPreviewParameterProvider
import com.example.designsystem.theme.FoodiesTheme
import com.example.model.CatalogModel
import com.example.model.ProductModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

@Composable
internal fun CatalogRoute(
    viewModel: CatalogViewModel
) {
    val state by viewModel.state.collectAsState()
    FoodiesTheme{
        CatalogScreen(
            state,
            { viewModel.addToCart(it) },
            { viewModel.removeFromCart(it) }
        )
    }
}

@Composable
internal fun CatalogScreen(
    state: CatalogUIState,
    onAddToCartClick: (Int) -> Unit,
    onRemoveFromCartClick: (Int) -> Unit
) {
    when (state) {
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
internal fun CatalogLoading() {
        CircularProgressIndicator()
}

@Composable
internal fun CatalogError() {
}

@Composable
internal fun CatalogLoadedData(
    state: CatalogUIState.Success,
    onAddToCartClick: (Int) -> Unit,
    onRemoveFromCartClick: (Int) -> Unit
) {
    val catalog = state.data

    val coroutineScope = rememberCoroutineScope()

    val selectedTabIndex = remember { mutableStateOf(0) }
    val listState = rememberLazyGridState()

    val scrollToItem = scroller(
        listState = listState,
        coroutineScope = coroutineScope,
        items = catalog.products
    )

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .mapNotNull { productIndex ->
                catalog.categories.indexOfFirst { category ->
                    category.id == catalog.products[productIndex].categoryId
                }
            }
            .distinctUntilChanged()
            .collectLatest {
                selectedTabIndex.value = it
            }
    }

    Column {

        CatalogTopBar(
            categories = catalog.categories,
            scrollToItem = scrollToItem,
            selectedTabIndex = selectedTabIndex,
            onFilterClick = {}
        )

        ListItems(
            state = listState,
            catalogItems = catalog.products,
            onAddToCartClick = onAddToCartClick,
            onRemoveFromCartClick = onRemoveFromCartClick
        )
    }
}

private fun scroller(
    listState: LazyGridState,
    coroutineScope: CoroutineScope,
    items: List<ProductModel>
): (Int) -> Unit = { categoryId ->
    coroutineScope.launch {
        val productIndex = items.indexOfFirst { it.categoryId == categoryId }
        listState.animateScrollToItem(index = productIndex)
    }
}

@Composable
internal fun ListItems(
    state: LazyGridState,
    catalogItems: List<ProductModel>,
    onAddToCartClick: (Int) -> Unit,
    onRemoveFromCartClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = state
    ) {
        items(catalogItems) { item ->
            Card(
                modifier = Modifier.padding(vertical = 5.dp)
            ) {
                Text(text = item.name)
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.clickable {
                            onRemoveFromCartClick.invoke(item.id)
                        },
                        text = "remove"
                    )
                    Text(text = item.countInCart.toString())
                    Text(
                        modifier = Modifier.clickable {
                            onAddToCartClick.invoke(item.id)
                        },
                        text = "add"
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun CatalogLoadedDataPreview(
    @PreviewParameter(CatalogPreviewParameterProvider::class)
    catalog: CatalogModel
) {
    CatalogLoadedData(
        state = CatalogUIState.Success(
            data = catalog
        ),
        onRemoveFromCartClick = {},
        onAddToCartClick = {}

    )
}