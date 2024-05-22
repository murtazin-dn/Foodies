package com.example.designsystem.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.designsystem.R
import com.example.designsystem.component.button.CardPriceButton
import com.example.designsystem.component.button.CounterButton
import com.example.designsystem.parameterprovider.CatalogProductInCardPreviewParameterProvider
import com.example.designsystem.parameterprovider.CatalogProductNotInCardPreviewParameterProvider
import com.example.designsystem.theme.FoodiesTheme
import com.example.model.ProductModel

@Composable
fun CatalogProductCard(
    modifier: Modifier = Modifier,
    product: ProductModel,
    onAddToCartClick: (Int) -> Unit,
    onRemoveFromCartClick: (Int) -> Unit
) {
    Box(modifier = modifier) {
        val tagIcon = when (product.tags.firstOrNull()?.id) {
            1 -> R.drawable.ic_sale
            2 -> R.drawable.ic_vegan
            3 -> R.drawable.ic_sale
            4 -> R.drawable.ic_sharp
            5 -> R.drawable.ic_sale
            null -> null
            else -> null
        }
        tagIcon?.let { tagIconId ->
            Image(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .zIndex(1f)
                    .padding(8.dp),
                painter = painterResource(id = tagIconId),
                contentDescription = "Tag icon",
            )
        }
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            )
        ) {
            AsyncImage(
                modifier = Modifier
                    .aspectRatio(1f)
                    .background(Color.Transparent),
                model = R.drawable.photo,
                //TODO: Replace with string resource
                contentDescription = "Image of ${product.name}",
                placeholder = painterResource(id = R.drawable.photo),
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    minLines = 2,
                    maxLines = 2,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
                    text = product.name
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                    text = "${product.measure} ${product.measureUnit}"
                )
                if (product.countInCart > 0) {
                    CounterButton(
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .fillMaxWidth(),
                        count = product.countInCart,
                        onAdd = {
                            onAddToCartClick.invoke(product.id)
                        },
                        onRemove = {
                            onRemoveFromCartClick.invoke(product.id)
                        }
                    )
                }else {
                    CardPriceButton(
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .fillMaxWidth(),
                        price = product.priceCurrent,
                        oldPrice = product.priceOld,
                        onClick = { onAddToCartClick.invoke(product.id) }
                    )
                }
            }
        }
    }

}


@Preview
@Composable
fun CatalogProductInCartPreview(
    @PreviewParameter(CatalogProductInCardPreviewParameterProvider::class)
    product: ProductModel
) {
    FoodiesTheme {
        Box(modifier = Modifier.fillMaxWidth()) {
            CatalogProductCard(
                modifier = Modifier.fillMaxWidth(0.5f),
                product = product,
                onAddToCartClick = {},
                onRemoveFromCartClick = {}
            )
        }

    }
}

@Preview
@Composable
fun CatalogProductNotInCartPreview(
    @PreviewParameter(CatalogProductNotInCardPreviewParameterProvider::class)
    product: ProductModel
) {
    FoodiesTheme {
        Box(modifier = Modifier.fillMaxWidth()) {
            CatalogProductCard(
                modifier = Modifier.fillMaxWidth(0.5f),
                product = product,
                onAddToCartClick = {},
                onRemoveFromCartClick = {}
            )
        }
    }
}