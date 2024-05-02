package com.example.domain.mapper

import com.example.data.model.Tag
import com.example.model.TagModel

internal fun Tag.toTagModel() = TagModel(
    id = this.id,
    name = this.name
)