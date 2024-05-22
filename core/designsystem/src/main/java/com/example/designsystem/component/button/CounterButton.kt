package com.example.designsystem.component.button

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import com.example.designsystem.R
import android.graphics.BlurMaskFilter
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.designsystem.ext.customShadow
import com.example.designsystem.theme.FoodiesTheme

@Composable
internal fun CounterButton(
    modifier: Modifier = Modifier,
    count: Int,
    onAdd: () -> Unit,
    onRemove: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Card(
            modifier = Modifier
                .size(40.dp)
                .customShadow(
                    color = Color(0xFF1F1F1F).copy(alpha = 0.5f),
                    offsetY = 4.dp,
                    blurRadius = 16.dp,
                    spread = -10.dp
                )
                .clickable { onAdd() },
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_plus),
                    //TODO use string resource
                    contentDescription = "Add",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

        }
        Text(
            style = MaterialTheme.typography.labelLarge.copy(Color.Black),
            //TODO use string resource
            text = count.toString(),
        )
        Card(
            modifier = Modifier
                .size(40.dp)
                .customShadow(
                    color = Color(0xFF1F1F1F).copy(alpha = 0.5f),
                    offsetY = 4.dp,
                    blurRadius = 16.dp,
                    spread = -10.dp
                )
                .clickable { onRemove() },
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_minus),
                    //TODO use string resource
                    contentDescription = "Remove",
                    tint = MaterialTheme.colorScheme.primary
                )
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
        CounterButton(
            modifier = Modifier.padding(16.dp),
            count = count,
            onAdd = {count++},
            onRemove = {count--}
        )
    }
}

