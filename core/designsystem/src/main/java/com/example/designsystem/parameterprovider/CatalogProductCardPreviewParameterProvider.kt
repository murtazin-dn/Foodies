package com.example.designsystem.parameterprovider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.model.ProductModel

class ProductInCardPreviewParameterProvider: PreviewParameterProvider<ProductModel> {
    override val values: Sequence<ProductModel> = sequenceOf(PreviewParameters.catalogProductInCart)
}
class ProductNotInCardPreviewParameterProvider: PreviewParameterProvider<ProductModel> {
    override val values: Sequence<ProductModel> = sequenceOf(PreviewParameters.catalogProductNotInCart)
}