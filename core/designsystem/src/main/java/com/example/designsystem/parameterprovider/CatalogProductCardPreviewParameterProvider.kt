package com.example.designsystem.parameterprovider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.model.ProductModel

class CatalogProductInCardPreviewParameterProvider: PreviewParameterProvider<ProductModel> {
    override val values: Sequence<ProductModel> = sequenceOf(PreviewParameters.catalogProductInCart)
}
class CatalogProductNotInCardPreviewParameterProvider: PreviewParameterProvider<ProductModel> {
    override val values: Sequence<ProductModel> = sequenceOf(PreviewParameters.catalogProductNotInCart)
}