package com.example.myfitnessapp.presentation.shared.components.recipes

import android.annotation.SuppressLint
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SearchAppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    categoryScrollPosition: Float,
    onCategoryScrollPositionChanged: (Float) -> Unit,
    toggleThemeIcon: ImageVector,
    toggleTheme: () -> Unit
) {

    Surface(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colors.surface
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                val focusManager = LocalFocusManager.current
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(10.dp),
                    value = query,
                    onValueChange = {
                        onQueryChanged(it)
                    },
                    label = {
                        Text(text = "Search")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null
                        )
                    },
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onExecuteSearch()
                            focusManager.clearFocus()
                        }
                    ),
                    textStyle = TextStyle(
                        color = MaterialTheme.colors.onSurface
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface
                    )
                )
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                ) {
                    val iconRow = createRef()
                    Row(
                        modifier = Modifier.constrainAs(iconRow) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                        }
                    ) {

                        IconButton(onClick = {}, modifier = Modifier.padding(2.dp)) {
                            Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null)

                        }
                    }
                }
            }

        }
    }

}

/*
 AddComposablesInLoopSample(
                                onExecuteSearch,
                                categoryScrollPosition,
                                selectedCategory,
                                onSelectedCategoryChanged,
                                onCategoryScrollPositionChanged

                            )
 */


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
private fun AddComposablesInLoopSample(
    onExecuteSearch: () -> Unit,
    categoryScrollPosition: Float,
    onSelectedCategoryChanged: (String) -> Unit,
    onCategoryScrollPositionChanged: (Float) -> Unit,) {

    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, bottom = 8.dp)
            .horizontalScroll(scrollState)
    ) {
        coroutineScope.launch {
            scrollState.animateScrollTo(categoryScrollPosition.toInt())
        }

    }
}

