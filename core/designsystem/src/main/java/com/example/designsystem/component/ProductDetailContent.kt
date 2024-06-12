package com.example.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.designsystem.R
import com.example.designsystem.component.button.ProductDetailButton
import com.example.designsystem.component.shadow.softlayer.SoftLayerShadowContainer
import com.example.designsystem.component.shadow.softlayer.softLayerShadow
import com.example.designsystem.parameterprovider.ProductInCardPreviewParameterProvider
import com.example.designsystem.theme.FoodiesTheme
import com.example.model.ProductModel

@Composable
fun ProductDetailContent(
    modifier: Modifier = Modifier,
    product: ProductModel,
    addToCart: () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .background(Color.White)
    ) {
        AsyncImage(
            modifier = Modifier
                .aspectRatio(1f)
                .background(Color.Transparent),
            model = R.drawable.photo,
            contentDescription = stringResource(
                R.string.image_of,
                product.name
            ),
            placeholder = painterResource(id = R.drawable.photo),
        )
        ProductDetailInfo(
            modifier = Modifier.padding(top = 24.dp, bottom = 21.dp),
            product = product
        )
        SoftLayerShadowContainer {
            Box(
                modifier = Modifier.softLayerShadow(
                    color = Color.Black.copy(alpha = 0.1f),
                    radius = 16.dp,
                    offset = DpOffset(y = -4.dp, x = 0.dp),
                    spread = -5.dp,
                    isAlphaContentClip = true
                )
            )
            {
                ProductDetailButton(
                    modifier = Modifier.padding(vertical = 13.dp, horizontal = 16.dp),
                    onClick = addToCart,
                    price = product.priceCurrent
                )
            }
        }
    }
}

@Composable
private fun ProductDetailInfo(
    modifier: Modifier = Modifier,
    product: ProductModel
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            style = MaterialTheme.typography.headlineLarge.copy(color = Color.Black),
            text = product.name
        )
        Text(
            modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray),
            text = product.description
        )
        NutritionInfo(
            modifier = Modifier.padding(top = 24.dp),
            nutritionList = listOf(
                Pair(
                    stringResource(R.string.title_weight),
                    "${product.measure} ${product.measureUnit}"
                ),
                Pair(
                    stringResource(R.string.title_energy_value),
                    stringResource(R.string.energy_value, product.energyPer100Grams)
                ),
                Pair(
                    stringResource(R.string.title_protein),
                    stringResource(R.string.protein, product.proteinsPer100Grams)
                ),
                Pair(
                    stringResource(R.string.title_fats),
                    stringResource(R.string.fats, product.fatsPer100Grams)
                ),
                Pair(
                    stringResource(R.string.title_carbonhydraties),
                    stringResource(R.string.carbonhydarates, product.carbohydratesPer100Grams)
                ),
            )
        )
    }
}

@Composable
private fun NutritionInfo(
    modifier: Modifier = Modifier,
    nutritionList: List<Pair<String, String>>
) {
    Column(modifier = modifier) {
        nutritionList.forEach { (label, value) ->
            Row(
                modifier = Modifier
                    .drawBehind {
                        val strokeWidth = 1.dp.toPx()
                        drawLine(
                            color = Color.LightGray,
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f),
                            strokeWidth = strokeWidth
                        )
                        drawLine(
                            color = Color.LightGray,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = strokeWidth
                        )
                    }
                    .padding(vertical = 13.dp, horizontal = 16.dp)
            ) {
                Text(
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray),
                    text = label
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                    text = value
                )
            }
        }
    }
}

@Preview
@Composable
private fun ProductDetailContentPreview(
    @PreviewParameter(ProductInCardPreviewParameterProvider::class)
    product: ProductModel
) {
    FoodiesTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            ProductDetailContent(
                product = product,
                addToCart = {}
            )
        }
    }
}