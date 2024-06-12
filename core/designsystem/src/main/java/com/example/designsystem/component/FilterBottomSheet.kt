package com.example.designsystem.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.designsystem.R
import com.example.designsystem.component.button.DefaultOrangeButton
import com.example.designsystem.parameterprovider.TagsModelParameterProvider
import com.example.model.FilterModel
import com.example.model.TagModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    modifier: Modifier = Modifier,
    tags: List<FilterModel>,
    sheetState: SheetState,
    onSelect: (List<FilterModel>) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        onDismissRequest = { onDismiss() },
        dragHandle = {},
        containerColor = Color.White
    ) {
        FilterContent(
            tags = tags,
            onSelect = onSelect
        )
    }
}

@Composable
private fun FilterContent(
    tags: List<FilterModel>,
    onSelect: (List<FilterModel>) -> Unit,
){
    Column(
        modifier = Modifier.padding(vertical = 32.dp, horizontal = 24.dp)
    ) {
        val checkedState = remember { tags.map { it.isSelected }.toMutableStateList() }

        Text(
            style = MaterialTheme.typography.titleLarge,
            text = stringResource(R.string.title_filter)
        )
        LazyColumn(modifier = Modifier.padding(vertical = 12.dp)) {
            itemsIndexed(tags) { index, tag ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyLarge,
                        text = tag.name
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Checkbox(
                        checked = checkedState[index],
                        onCheckedChange = {
                            checkedState[index] = !checkedState[index]
                        }
                    )
                }
            }
        }
        DefaultOrangeButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onSelect(
                    tags.mapIndexed { index, tag ->
                        tag.copy(isSelected = checkedState[index])
                    }
                )
            },
            text = stringResource(R.string.done)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun FilterBottomSheetPreview(
    @PreviewParameter(TagsModelParameterProvider::class)
    tags: List<TagModel>
) {
    FilterContent(tags = tags.map { FilterModel(id = it.id, name = it.name) }) {

    }
}