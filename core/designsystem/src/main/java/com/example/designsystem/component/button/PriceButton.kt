package com.example.designsystem.component.button

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.designsystem.R
import com.example.designsystem.component.shadow.softlayer.SoftLayerShadowContainer
import com.example.designsystem.component.shadow.softlayer.softLayerShadow
import com.example.designsystem.theme.FoodiesTheme

@Composable
internal fun CardPriceButton(
    modifier: Modifier = Modifier,
    price: Long,
    oldPrice: Long? = null,
    onClick: () -> Unit
) {
    SoftLayerShadowContainer {
        Box(
            modifier = modifier
        ) {
            Button(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .softLayerShadow(
                        color = Color(0xFF1F1F1F).copy(alpha = 0.2f),
                        radius = 16.dp,
                        offset = DpOffset(y = 4.dp, x = 0.dp),
                        spread = -10.dp,
                    ),
                onClick = onClick,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    style = MaterialTheme.typography.labelLarge.copy(Color.Black),
                    text = stringResource(R.string.price, price),
                )
                oldPrice?.let {
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

@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5)
@Composable
private fun CardPriceButtonPreview() {
    FoodiesTheme {
        CardPriceButton(
            modifier = Modifier.padding(16.dp),
            price = 500,
            onClick = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5)
@Composable
private fun CardPriceWithSaleButtonPreview() {
    FoodiesTheme {
        CardPriceButton(
            modifier = Modifier.padding(16.dp),
            price = 500,
            oldPrice = 800,
            onClick = {}
        )
    }
}

