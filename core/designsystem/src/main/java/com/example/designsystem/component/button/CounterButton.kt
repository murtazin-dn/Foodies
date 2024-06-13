package com.example.designsystem.component.button

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import com.example.designsystem.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.designsystem.component.shadow.softlayer.SoftLayerShadowContainer
import com.example.designsystem.component.shadow.softlayer.softLayerShadow
import com.example.designsystem.theme.FoodiesTheme

@Composable
internal fun CounterButtonCatalog(
    modifier: Modifier = Modifier,
    count: Int,
    onAdd: () -> Unit,
    onRemove: () -> Unit
) {
    BaseCounter(
        modifier = modifier,
        count = count,
        onAdd = onAdd,
        onRemove = onRemove,
        shadowEnabled = true
    )
}

@Composable
internal fun CounterButtonCart(
    modifier: Modifier = Modifier,
    count: Int,
    onAdd: () -> Unit,
    onRemove: () -> Unit
) {
    BaseCounter(
        modifier = modifier,
        count = count,
        onAdd = onAdd,
        onRemove = onRemove,
        buttonColor = MaterialTheme.colorScheme.tertiary
    )
}

@Composable
private fun BaseCounter(
    modifier: Modifier = Modifier,
    count: Int,
    onAdd: () -> Unit,
    onRemove: () -> Unit,
    shadowEnabled: Boolean = false,
    buttonColor: Color = Color.White
) {
    val shadowModifier = if (shadowEnabled) {
        Modifier
            .softLayerShadow(
                color = Color(0xFF1F1F1F).copy(alpha = 0.2f),
                radius = 16.dp,
                offset = DpOffset(y = 4.dp, x = 0.dp),
                spread = -10.dp,
            )
    } else {
        Modifier
    }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SoftLayerShadowContainer {
            Card(
                modifier = shadowModifier
                    .size(40.dp)
                    .clickable { onAdd() },
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = buttonColor)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.Center),
                        painter = painterResource(id = R.drawable.ic_plus),
                        contentDescription = stringResource(R.string.description_add),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

            }
        }

        Text(
            modifier = Modifier.defaultMinSize(minWidth = 47.dp),
            style = MaterialTheme.typography.labelLarge.copy(Color.Black),
            text = count.toString(),
            textAlign = TextAlign.Center,
        )
        SoftLayerShadowContainer {
            Card(
                modifier = shadowModifier
                    .size(40.dp)
                    .clickable { onRemove() },
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = buttonColor)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.Center),
                        painter = painterResource(id = R.drawable.ic_minus),
                        contentDescription = stringResource(R.string.description_remove),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

    }

}


@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5)
@Composable
private fun CounterButtonButtonPreview() {
    FoodiesTheme {
        var count by remember {
            mutableIntStateOf(0)
        }
        CounterButtonCatalog(
            modifier = Modifier.padding(16.dp),
            count = count,
            onAdd = { count++ },
            onRemove = { count-- }
        )
    }
}

