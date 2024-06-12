package com.example.designsystem.component.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.R
import com.example.designsystem.theme.FoodiesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartTopBar(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    Column(modifier = modifier) {
        TopAppBar(
            title = {
                Text(
                    modifier = Modifier.padding(start = 32.dp),
                    style = MaterialTheme.typography.titleLarge,
                    text = stringResource(R.string.cart)
                )
            },
            navigationIcon = {
                Icon(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clickable { onBack() },
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = stringResource(R.string.back),
//                tint = MaterialTheme.colorScheme.primary
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White,
                navigationIconContentColor = MaterialTheme.colorScheme.primary,
                titleContentColor = Color.Black
            )
        )
    }

}

@Preview
@Composable
private fun CartTopBarPreview(){
    FoodiesTheme {
        CartTopBar(
            onBack = {}
        )
    }
}