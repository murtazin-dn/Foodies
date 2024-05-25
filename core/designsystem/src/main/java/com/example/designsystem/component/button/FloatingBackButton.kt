package com.example.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.R
import com.example.designsystem.theme.FoodiesTheme

@Composable
fun FloatingBackButton(
    modifier: Modifier = Modifier,
    onClick:() -> Unit
){
    FloatingActionButton(
        modifier = modifier.size(44.dp),
        shape = FloatingActionButtonDefaults.largeShape,
        containerColor = Color.White,
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = stringResource(R.string.back),
            tint = Color.Black
        )
    }
}

@Preview
@Composable
private fun FloatingBackButtonPreview(){
    FoodiesTheme {
        Box(modifier = Modifier.background(Color.White)){
            FloatingBackButton(
                modifier = Modifier.padding(16.dp),
                onClick = {}
            )
        }

    }
}