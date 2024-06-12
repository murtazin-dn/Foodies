package com.example.designsystem.component.topbar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.designsystem.R
import com.example.designsystem.parameterprovider.CatalogPreviewParameterProvider
import com.example.designsystem.theme.FoodiesTheme
import com.example.designsystem.theme.Typography
import com.example.model.CatalogModel
import com.example.model.CategoryModel


@Composable
fun CatalogTopBar(
    modifier: Modifier = Modifier,
    categories: List<CategoryModel>,
    scrollToItem: (Int) -> Unit,
    selectedTabIndex: State<Int>,
    searchState: String?,
    filterEnabled: Boolean,
    filterCount: Int,
    onSearch: (String?) -> Unit,
    onFilterClick: () -> Unit
) {
    if (searchState != null) {
        TopBarSearch(
            searchState = searchState,
            onSearch = { value ->
                onSearch(value)
            },
            onBack = { onSearch(null) }
        )
    } else {
        Column(
            modifier = Modifier
                .background(Color.White)
        ) {
            TopBarTop(
                filterEnabled = filterEnabled,
                filterCount = filterCount,
                onFilterClick = {
                    onFilterClick()
                },
                onSearchClick = {
                    onSearch("")
                })
            TopBarBottom(selectedTabIndex, scrollToItem, categories)
        }
    }
}

@Composable
private fun TopBarSearch(
    searchState: String,
    onSearch: (String) -> Unit,
    onBack: () -> Unit
) {
    OutlinedTextField(
        value = searchState,
        onValueChange = onSearch,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        placeholder = {
            Text(
                style = MaterialTheme.typography.bodyLarge.copy(Color.Gray),
                text = stringResource(R.string.hint_search_product)
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = stringResource(id = R.string.back),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { onBack() }
            )
        },
        trailingIcon = {
            if (searchState != "")
                Icon(
                    painter = painterResource(id = R.drawable.ic_cancel),
                    contentDescription = stringResource(id = R.string.clear),
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { onSearch("") }
                )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White
        )
    )
}

@Composable
private fun TopBarBottom(
    selectedTabIndex: State<Int>,
    scrollToItem: (Int) -> Unit,
    categories: List<CategoryModel>
) {
    Tabs(
        selectedTabIndex = selectedTabIndex.value,
        scrollToItem = scrollToItem,
        tabItems = categories
    )
    Spacer(
        modifier = Modifier
            .height(8.dp)
            .fillMaxWidth()
            .background(Color.White)
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopBarTop(
    filterEnabled: Boolean,
    filterCount: Int,
    onFilterClick: () -> Unit,
    onSearchClick: () -> Unit
) {

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White
        ),
        modifier = Modifier,
        title = {
            Icon(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(R.string.logo),
                tint = MaterialTheme.colorScheme.primary
            )
        },
        navigationIcon = {
            if (!filterEnabled)
                Box {
                    Icon(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .clickable { onFilterClick() },
                        painter = painterResource(id = R.drawable.ic_filter),
                        contentDescription = stringResource(R.string.filter),
                        tint = Color.Black
                    )
                    if (filterCount > 0)
                        Text(
                            modifier = Modifier.align(Alignment.TopEnd),
                            style = MaterialTheme.typography.bodySmall,
                            text = filterCount.toString()
                        )
                }

        },
        actions = {
            Icon(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable { onSearchClick() },
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = stringResource(R.string.search),
                tint = Color.Black
            )
        }
    )

}

@Composable
private fun Tabs(
    modifier: Modifier = Modifier,
    selectedTabIndex: Int,
    scrollToItem: (Int) -> Unit,
    tabItems: List<CategoryModel>
) {
    ScrollableTabRow(
        modifier = modifier,
        containerColor = Color.White,
        selectedTabIndex = selectedTabIndex,
        edgePadding = 16.dp,
        indicator = { tabPositions ->
            CustomTabIndicator(
                tabPositions = tabPositions,
                selectedTabIndex = selectedTabIndex
            )
        },
        divider = {}
    ) {
        tabItems.forEachIndexed { index, category ->
            Tab(
                modifier = Modifier
                    .zIndex(1f)
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                selected = selectedTabIndex == index,
                onClick = { scrollToItem(category.id) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Black
            ) {
                Text(
                    style = MaterialTheme.typography.labelLarge,
                    text = category.name
                )
            }
        }
    }
}

@Composable
fun CustomTabIndicator(
    tabPositions: List<TabPosition>,
    selectedTabIndex: Int
) {
    val currentTabPosition = tabPositions[selectedTabIndex]
    val indicatorWidth by animateFloatAsState(targetValue = currentTabPosition.width.value)
    val indicatorOffset by animateFloatAsState(targetValue = currentTabPosition.left.value)

    Box(
        Modifier
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.BottomStart)
            .offset(x = indicatorOffset.dp)
            .width(indicatorWidth.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.primary)
            .zIndex(0f)
    )
}

@Preview
@Composable
fun CatalogTopBarPreview(
    @PreviewParameter(CatalogPreviewParameterProvider::class)
    catalog: CatalogModel
) {
    val state = remember {
        mutableIntStateOf(0)
    }
    var filterEnabled by remember {
        mutableStateOf(false)
    }
    val searchState: MutableState<String?> = remember {
        mutableStateOf(null)
    }
    val searchEnabled: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
    FoodiesTheme {
        CatalogTopBar(
            categories = catalog.categories,
            scrollToItem = {},
            selectedTabIndex = state,
            filterEnabled = filterEnabled,
            filterCount = 3,
            searchState = searchState.value,
            onSearch = {
                searchState.value = it
            },
            onFilterClick = {
                filterEnabled = true
            }
        )
    }
}