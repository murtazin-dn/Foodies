package com.example.designsystem.component.topbar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.designsystem.R
import com.example.designsystem.parameterprovider.CatalogPreviewParameterProvider
import com.example.designsystem.theme.FoodiesTheme
import com.example.model.CatalogModel
import com.example.model.CategoryModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogTopBar(
    categories: List<CategoryModel>,
    scrollToItem: (Int) -> Unit,
    selectedTabIndex: State<Int>,
    onFilterClick: () -> Unit
) {
    Column {
        CenterAlignedTopAppBar(
            modifier = Modifier,
            title = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logo),
                    //TODO("заменить на ресурс")
                    contentDescription = "Logo",
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            navigationIcon = {
                Icon(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clickable { onFilterClick() },
                    painter = painterResource(id = R.drawable.ic_filter),
                    //TODO("заменить на ресурс")
                    contentDescription = "Filter",
                    tint = Color.Black
                )
            },
            actions = {
                Icon(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .clickable { onFilterClick() },
                    painter = painterResource(id = R.drawable.ic_search),
                    //TODO("заменить на ресурс")
                    contentDescription = "Search",
                    tint = Color.Black
                )
            }
        )
        Tabs(
            selectedTabIndex = selectedTabIndex.value,
            scrollToItem = scrollToItem,
            tabItems = categories
        )
    }
}

@Composable
private fun Tabs(
    selectedTabIndex: Int,
    scrollToItem: (Int) -> Unit,
    tabItems: List<CategoryModel>
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        edgePadding = 16.dp,
        contentColor = MaterialTheme.colorScheme.primary,
        indicator = { tabPositions ->
            CustomTabIndicator(
                tabPositions = tabPositions,
                selectedTabIndex = selectedTabIndex
            )
        }
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
    FoodiesTheme {
        CatalogTopBar(
            categories = catalog.categories,
            scrollToItem = {},
            selectedTabIndex = mutableStateOf(0),
            onFilterClick = {}
        )
    }
}