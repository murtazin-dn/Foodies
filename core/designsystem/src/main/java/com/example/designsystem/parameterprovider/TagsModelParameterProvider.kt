package com.example.designsystem.parameterprovider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.model.TagModel

class TagsModelParameterProvider: PreviewParameterProvider<List<TagModel>> {
    override val values = sequenceOf(PreviewParameters.tags)
}