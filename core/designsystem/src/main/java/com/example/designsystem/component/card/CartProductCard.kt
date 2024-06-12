package com.example.designsystem.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.designsystem.R
import com.example.designsystem.component.button.CounterButtonCart
import com.example.designsystem.component.button.CounterButtonCatalog
import com.example.designsystem.parameterprovider.ProductInCardPreviewParameterProvider
import com.example.designsystem.theme.FoodiesTheme
import com.example.model.ProductModel

@Composable
fun CartProductCard(
    modifier: Modifier = Modifier,
    product: ProductModel,
    addToCart: () -> Unit,
    removeFromCart: () -> Unit
){
    Column(
        modifier = modifier
            .background(Color.White)
            .fillMaxWidth()

    )  {
        Row(
            modifier = modifier
                .padding(16.dp)
        ){
            AsyncImage(
                modifier = Modifier
                    .size(96.dp)
                    .background(Color.Transparent),
                model = R.drawable.photo,
                contentDescription = stringResource(R.string.image_of, product.name),
                placeholder = painterResource(id = R.drawable.photo),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.bodyMedium.copy(Color.Black),
                    minLines = 2,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CounterButtonCart(
                        count = product.countInCart,
                        onAdd = addToCart,
                        onRemove = removeFromCart
                    )
                    Column (
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            style = MaterialTheme.typography.labelLarge.copy(Color.Black),
                            text = stringResource(R.string.price, product.priceCurrent),
                        )
                        product.priceOld?.let {
                            Text(
                                modifier = Modifier.padding(start = 8.dp),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = Color.Gray,
                                    textDecoration = TextDecoration.LineThrough
                                ),
                                text = stringResource(R.string.price, it),
                            )
                        }
                    }
                }
            }
        }
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color.LightGray
        )
    }

}

@Preview
@Composable
fun CartProductCardPreview(
    @PreviewParameter(ProductInCardPreviewParameterProvider::class)
    product: ProductModel
){
    FoodiesTheme {
        CartProductCard(
            product = product,
            addToCart = {},
            removeFromCart = {}
        )
    }
}