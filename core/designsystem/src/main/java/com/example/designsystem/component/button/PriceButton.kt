package com.example.designsystem.component.button

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.FoodiesTheme

@Composable
internal fun CardPriceButton(
    modifier: Modifier = Modifier,
    price: Long,
    oldPrice: Long? = null,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
    ) {
        Text(
            style = MaterialTheme.typography.labelLarge.copy(Color.Black),
            //TODO use string resource
            text = "$price ₽",
        )
        oldPrice?.let {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    textDecoration = TextDecoration.LineThrough
                ),
                //TODO use string resource
                text = "$it ₽",
            )
        }

    }
}

@Preview
@Composable
private fun CardPriceButtonPreview() {
    FoodiesTheme {
        CardPriceButton(
            price = 500,
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun CardPriceWithSaleButtonPreview() {
    FoodiesTheme {
        CardPriceButton(
            price = 500,
            oldPrice = 800,
            onClick = {}
        )
    }
}