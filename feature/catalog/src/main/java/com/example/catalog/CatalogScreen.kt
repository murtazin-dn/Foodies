package com.example.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.designsystem.component.FilterBottomSheet
import com.example.designsystem.component.button.CatalogCartButton
import com.example.designsystem.component.card.CatalogProductCard
import com.example.designsystem.component.card.ReloadCard
import com.example.designsystem.component.shadow.elevation.AlphaContentElevationShadow
import com.example.designsystem.component.shadow.softlayer.SoftLayerShadowContainer
import com.example.designsystem.component.shadow.softlayer.softLayerShadow
import com.example.designsystem.component.topbar.CatalogTopBar
import com.example.designsystem.parameterprovider.CatalogPreviewParameterProvider
import com.example.designsystem.theme.FoodiesTheme
import com.example.model.CatalogModel
import com.example.model.FilterModel
import com.example.model.ProductModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

@Composable
internal fun CatalogRoute(
    viewModel: CatalogViewModel,
    navigateToProductDetail: (Int) -> Unit,
    navigateToCart: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val searchState by viewModel.searchState.collectAsState()
    val filterState by viewModel.filterState.collectAsState()
    FoodiesTheme {
        CatalogScreen(
            state = state,
            searchState = searchState,
            filterState = filterState,
            onAddToCartClick = { viewModel.obtainEvent(CatalogEvents.AddToCart(it)) },
            onRemoveFromCartClick = { viewModel.obtainEvent(CatalogEvents.RemoveFromCartCart(it)) },
            navigateToProductDetail = navigateToProductDetail,
            navigateToCart = navigateToCart,
            onSearch = { viewModel.obtainEvent(CatalogEvents.Search(it)) },
            onFilter = { viewModel.obtainEvent(CatalogEvents.Filter(it)) },
            retry = {viewModel.obtainEvent(CatalogEvents.Reload)}
        )
    }
}

@Composable
internal fun CatalogScreen(
    state: CatalogUIState,
    searchState: String?,
    filterState: List<FilterModel>,
    onAddToCartClick: (Int) -> Unit,
    onRemoveFromCartClick: (Int) -> Unit,
    navigateToProductDetail: (Int) -> Unit,
    navigateToCart: () -> Unit,
    onSearch: (String?) -> Unit,
    onFilter: (List<FilterModel>) -> Unit,
    retry: () -> Unit
) {
    when (state) {
        is CatalogUIState.Error -> CatalogError(retry = retry)
        CatalogUIState.Loading -> CatalogLoading()
        is CatalogUIState.Success -> CatalogLoadedData(
            state = state,
            searchState = searchState,
            filterState = filterState,
            onAddToCartClick = onAddToCartClick,
            onRemoveFromCartClick = onRemoveFromCartClick,
            navigateToProductDetail = navigateToProductDetail,
            navigateToCart = navigateToCart,
            onSearch = onSearch,
            onFilter = onFilter
        )
    }
}

@Composable
internal fun CatalogEmptySearchValue(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray),
            text = stringResource(R.string.label_empty_search_value),
            textAlign = TextAlign.Center
        )
    }
}
@Composable
internal fun CatalogEmptySearch(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray),
            text = stringResource(R.string.label_empty_search),
            textAlign = TextAlign.Center
        )
    }
}
@Composable
internal fun CatalogEmptyFilter(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray),
            text = stringResource(R.string.label_empty_filter),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
internal fun CatalogLoading() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
internal fun CatalogError(
    retry: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        ReloadCard(
            modifier = Modifier.align(Alignment.Center),
            onReload = retry
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CatalogLoadedData(
    state: CatalogUIState.Success,
    searchState: String?,
    filterState: List<FilterModel>,
    onAddToCartClick: (Int) -> Unit,
    onRemoveFromCartClick: (Int) -> Unit,
    onSearch: (String?) -> Unit,
    onFilter: (List<FilterModel>) -> Unit,
    navigateToProductDetail: (Int) -> Unit,
    navigateToCart: () -> Unit
) {
    val catalog = state.data

    val coroutineScope = rememberCoroutineScope()

    val filterCount by remember { mutableIntStateOf(0) }
    var filterEnabled by remember { mutableStateOf(false) }
    val selectedTabIndex = remember { mutableIntStateOf(0) }

    val sheetState = rememberModalBottomSheetState()
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


    Scaffold { contentPadding ->
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(contentPadding)
        ) {
            SoftLayerShadowContainer {
                Box(
                    modifier = Modifier.softLayerShadow(
                        color = Color.Black.copy(alpha = 0.1f),
                        radius = 16.dp,
                        offset = DpOffset(y = 4.dp, x = 0.dp),
                        spread = -5.dp,
                        isAlphaContentClip = true
                    )
                ) {
                    CatalogTopBar(
                        categories = catalog.categories,
                        scrollToItem = scrollToItem,
                        selectedTabIndex = selectedTabIndex,
                        filterEnabled = filterEnabled,
                        filterCount = filterCount,
                        searchState = searchState,
                        onSearch = onSearch,
                        onFilterClick = {
                            filterEnabled = true
                        }
                    )
                }
            }
            Column(
                modifier = Modifier
                    .background(Color.White)
            ) {
                when(state){
                    is CatalogUIState.Success.Data -> {
                        ListItems(
                            modifier = Modifier.weight(1f),
                            state = listState,
                            catalogItems = catalog.products,
                            onAddToCartClick = onAddToCartClick,
                            onRemoveFromCartClick = onRemoveFromCartClick,
                            navigateToProductDetail = navigateToProductDetail
                        )
                        if (catalog.sum > 0) {
                            SoftLayerShadowContainer {
                                Box(
                                    modifier = Modifier
                                        .background(Color.White)
                                        .fillMaxWidth()
                                        .softLayerShadow(
                                            color = Color.Black.copy(alpha = 0.1f),
                                            radius = 16.dp,
                                            offset = DpOffset(y = (-4).dp, x = 0.dp),
                                            spread = (-5).dp,
                                            isAlphaContentClip = true
                                        )
                                ) {
                                    CatalogCartButton(
                                        modifier = Modifier.padding(12.dp),
                                        onClick = navigateToCart,
                                        price = catalog.sum
                                    )
                                }
                            }

                        }
                    }
                    is CatalogUIState.Success.EmptyFilter -> CatalogEmptyFilter()
                    is CatalogUIState.Success.EmptySearch -> CatalogEmptySearch()
                    is CatalogUIState.Success.EmptySearchValue -> CatalogEmptySearchValue()
                }

            }
        }
        if (filterEnabled) {
            FilterBottomSheet(
                tags = filterState,
                sheetState = sheetState,
                onSelect = {
                    onFilter(it)
                    filterEnabled = false
                },
                onDismiss = { filterEnabled = false }
            )
        }

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
    modifier: Modifier = Modifier,
    state: LazyGridState,
    catalogItems: List<ProductModel>,
    onAddToCartClick: (Int) -> Unit,
    onRemoveFromCartClick: (Int) -> Unit,
    navigateToProductDetail: (Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        state = state,
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(catalogItems) { item ->
            CatalogProductCard(
                modifier = Modifier.clickable {
                    navigateToProductDetail(item.id)
                },
                product = item,
                onRemoveFromCartClick = onRemoveFromCartClick,
                onAddToCartClick = onAddToCartClick
            )
        }
    }
}


@Preview
@Composable
private fun CatalogLoadedDataPreview(
    @PreviewParameter(CatalogPreviewParameterProvider::class)
    catalog: CatalogModel
) {

    FoodiesTheme {
        CatalogLoadedData(
            state = CatalogUIState.Success.Data(
                data = catalog
            ),
            searchState = null,
            filterState = listOf(),
            onAddToCartClick = {},
            onRemoveFromCartClick = {},
            navigateToProductDetail = {},
            navigateToCart = {},
            onSearch = {},
            onFilter = {}
        )
    }
}