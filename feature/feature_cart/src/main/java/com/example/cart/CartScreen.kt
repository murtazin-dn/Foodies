package com.example.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.designsystem.component.button.OrderButton
import com.example.designsystem.component.card.CartProductCard
import com.example.designsystem.component.card.ReloadCard
import com.example.designsystem.component.shadow.softlayer.SoftLayerShadowContainer
import com.example.designsystem.component.shadow.softlayer.softLayerShadow
import com.example.designsystem.component.topbar.CartTopBar
import com.example.designsystem.theme.FoodiesTheme
import com.example.feature_cart.R
import com.example.model.ProductsModel


@Composable
internal fun CartRoute(
    viewModel: CartViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    FoodiesTheme {
        CartScreen(
            state = state,
            onBack = onBack,
            addToCart = viewModel::addToCart,
            removeFromCart = viewModel::removeFromCart,
            retry = viewModel::reload
        )
    }
}


@Composable
internal fun CartScreen(
    state: CartUIState,
    onBack: () -> Unit,
    retry: () -> Unit,
    addToCart: (Int) -> Unit,
    removeFromCart: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        SoftLayerShadowContainer {
            Box(
                modifier = Modifier
                    .softLayerShadow(
                        color = Color.Black.copy(alpha = 0.1f),
                        radius = 16.dp,
                        offset = DpOffset(y = 4.dp, x = 0.dp),
                        spread = -5.dp,
                        isAlphaContentClip = true
                    )
                    .zIndex(1f)
            ) {
                CartTopBar(
                    onBack = onBack
                )
            }
        }


        when (state) {
            CartUIState.Empty -> CartEmptyScreen()
            is CartUIState.Error -> CartErrorScreen(retry)
            CartUIState.Loading -> CartLoadingScreen()
            is CartUIState.Success -> CartSuccessScreen(
                state.cart,
                addToCart = addToCart,
                removeFromCart = removeFromCart
            )
        }
    }
}

@Composable
private fun CartSuccessScreen(
    cart: ProductsModel,
    addToCart: (Int) -> Unit,
    removeFromCart: (Int) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn (
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = 72.dp)
        ) {
            items(cart.products) { product ->
                CartProductCard(
                    product = product,
                    addToCart = { addToCart(product.id) },
                    removeFromCart = { removeFromCart(product.id) }
                )
            }
        }
        SoftLayerShadowContainer {
            Box(
                modifier = Modifier
                    .height(72.dp)
                    .fillMaxWidth()
                    .background(Color.White)
                    .softLayerShadow(
                        color = Color.Black.copy(alpha = 0.1f),
                        radius = 16.dp,
                        offset = DpOffset(y = (-4).dp, x = 0.dp),
                        spread = (-5).dp,
                        isAlphaContentClip = true
                    )
                    .zIndex(2f)
                    .align(Alignment.BottomCenter)
            ){
                OrderButton(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center),
                    onClick = { },
                    price = cart.sum
                )
            }
        }
    }

}


@Composable
private fun CartEmptyScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray),
            text = stringResource(R.string.label_cart_empty),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun CartErrorScreen(
    retry: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ReloadCard(
            modifier = Modifier.align(Alignment.Center),
            onReload = retry
        )
    }
}

@Composable
private fun CartLoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}


