package com.example.designsystem.component.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.designsystem.R
import com.example.designsystem.component.button.RetryButton

@Composable
fun ReloadCard(
    modifier: Modifier = Modifier,
    text: String = stringResource(id = R.string.error),
    onReload: () -> Unit
){
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray),
                text = text
            )
            RetryButton(
                onClick = onReload
            )
        }
    }
}