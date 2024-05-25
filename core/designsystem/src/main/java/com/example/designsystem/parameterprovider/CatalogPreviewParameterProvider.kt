package com.example.designsystem.parameterprovider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.model.CatalogModel

class CatalogPreviewParameterProvider : PreviewParameterProvider<CatalogModel> {

    override val values: Sequence<CatalogModel> = sequenceOf(PreviewParameters.catalog)
}

