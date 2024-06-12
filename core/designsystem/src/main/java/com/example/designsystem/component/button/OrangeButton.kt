package com.example.designsystem.component.button

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.R
import com.example.designsystem.ext.formatNumberWithSpaces
import com.example.designsystem.theme.FoodiesTheme


@Composable
fun CatalogCartButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    price: Long
){
    BaseOrangeIconButton(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        text = stringResource(R.string.price, price.formatNumberWithSpaces())
    )
}

@Composable
fun RetryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    DefaultOrangeButton(modifier = modifier, onClick = onClick, text = stringResource(R.string.reload))
}
@Composable
fun OrderButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    price: Long
){
    DefaultOrangeButton(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        text = stringResource(
            R.string.order,
            stringResource(R.string.price, price.formatNumberWithSpaces())
        )
    )
}

@Composable
fun ProductDetailButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    price: Long
){
    DefaultOrangeButton(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        text = stringResource(
            R.string.add_to_cart,
            stringResource(R.string.price, price.formatNumberWithSpaces())
        )
    )
}



@Composable
private fun BaseOrangeIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),

    ){
        Icon(
            painter = painterResource(id = R.drawable.ic_cart),
            //TODO string resource
            contentDescription = "cart button",
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            style = MaterialTheme.typography.labelLarge.copy(color = Color.White),
            text = text
        )
    }
}

@Composable
fun DefaultOrangeButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),

    ){
        Text(
            style = MaterialTheme.typography.labelLarge.copy(color = Color.White),
            text = text
        )
    }
}

@Preview
@Composable
private fun OrderButtonPreview() {
    FoodiesTheme {
        OrderButton(
            onClick = {},
            price = 1349
        )
    }
}
@Preview
@Composable
private fun ProductDetailButtonPreview() {
    FoodiesTheme {
        ProductDetailButton(
            onClick = {},
            price = 1349
        )
    }
}
@Preview
@Composable
private fun CatalogCartButtonPreview() {
    FoodiesTheme {
        BaseOrangeIconButton(
            onClick = {},
            text = "480"
        )
    }
}
@Preview
@Composable
private fun BaseOrangeIconButtonPreview() {
    FoodiesTheme {
        BaseOrangeIconButton(
            onClick = {},
            text = "Button"
        )
    }
}
@Preview
@Composable
private fun BaseOrangeButtonPreview() {
    FoodiesTheme {
        DefaultOrangeButton(
            onClick = {},
            text = "Button"
        )
    }
}