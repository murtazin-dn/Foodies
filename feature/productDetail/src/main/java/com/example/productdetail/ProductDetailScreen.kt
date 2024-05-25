package com.example.productdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.designsystem.component.ProductDetailContent
import com.example.designsystem.component.button.FloatingBackButton
import com.example.designsystem.parameterprovider.ProductInCardPreviewParameterProvider
import com.example.designsystem.theme.FoodiesTheme
import com.example.model.ProductModel

@Composable
internal fun ProductDetailRoute(
    viewModel: ProductDetailViewModel,
    onBack: () -> Unit
){
    val state by viewModel.state.collectAsState()
    ProductDetailScreen(
        state = state,
        reload = viewModel::reload,
        addToCart = viewModel::addToCart,
        onBack = onBack
    )
}

@Composable
internal fun ProductDetailScreen(
    state: ProductDetailUIState,
    reload: () -> Unit,
    addToCart: () -> Unit,
    onBack: () -> Unit
){
    FoodiesTheme {
        when(state){
            ProductDetailUIState.Empty -> ProductDetailEmptyScreen(reload)
            is ProductDetailUIState.Error -> ProductDetailErrorScreen(reload)
            ProductDetailUIState.Loading -> ProductDetailLoadingScreen()
            is ProductDetailUIState.Success -> ProductDetailSuccessScreen(
                state = state,
                onBack = onBack,
                addToCart = addToCart
            )
        }
    }
}

@Composable
internal fun ProductDetailSuccessScreen(
    state: ProductDetailUIState.Success,
    onBack: () -> Unit,
    addToCart: () -> Unit
){
    val product = state.product
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        ProductDetailContent(
            product = product,
            addToCart = addToCart
        )
        FloatingBackButton(
            modifier = Modifier.padding(16.dp),
            onClick = onBack
        )

    }
}

@Composable
internal fun ProductDetailEmptyScreen(
    retry: () -> Unit
){

}

@Composable
internal fun ProductDetailErrorScreen(
    retry: () -> Unit
){

}

@Composable
internal fun ProductDetailLoadingScreen(){

}

@Preview
@Composable
private fun ProductDetailSuccessScreenPreview(
    @PreviewParameter(ProductInCardPreviewParameterProvider::class)
    product: ProductModel
){
    ProductDetailSuccessScreen(
        state = ProductDetailUIState.Success(product),
        onBack = {},
        addToCart = {}
    )
}