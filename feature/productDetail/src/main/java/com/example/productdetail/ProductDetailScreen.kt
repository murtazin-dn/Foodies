package com.example.productdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.designsystem.component.ProductDetailContent
import com.example.designsystem.component.button.FloatingBackButton
import com.example.designsystem.component.button.RetryButton
import com.example.designsystem.component.card.ReloadCard
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            when (state) {
                ProductDetailUIState.Empty -> ProductDetailEmptyScreen(reload)
                is ProductDetailUIState.Error -> ProductDetailErrorScreen(reload)
                ProductDetailUIState.Loading -> ProductDetailLoadingScreen()
                is ProductDetailUIState.Success -> ProductDetailSuccessScreen(
                    state = state,
                    addToCart = addToCart
                )
            }

            FloatingBackButton(
                modifier = Modifier.padding(16.dp),
                onClick = onBack
            )
        }
    }
}

@Composable
internal fun ProductDetailSuccessScreen(
    state: ProductDetailUIState.Success,
    addToCart: () -> Unit
){
    val product = state.product
        ProductDetailContent(
            product = product,
            addToCart = addToCart
        )
}

@Composable
internal fun ProductDetailEmptyScreen(
    retry: () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        ReloadCard(
            onReload = retry,
            text = stringResource(id = R.string.title_not_found)
        )
    }
}

@Composable
internal fun ProductDetailErrorScreen(
    retry: () -> Unit
){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        ReloadCard(
            onReload = retry
        )
    }
}

@Composable
internal fun ProductDetailLoadingScreen(){
    Box(modifier = Modifier.fillMaxSize()){
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Preview
@Composable
private fun ProductDetailSuccessScreenPreview(
    @PreviewParameter(ProductInCardPreviewParameterProvider::class)
    product: ProductModel
){
    ProductDetailSuccessScreen(
        state = ProductDetailUIState.Success(product),
        addToCart = {}
    )
}