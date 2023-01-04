package com.example.myfitnessapp.presentation.shared.components.recipes

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier,
    onActionClick: () -> Unit
) {
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = modifier,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier
                    .padding(16.dp),
                backgroundColor = MaterialTheme.colors.onSurface,
                action = {
                    data.actionLabel?.let {
                        TextButton(onClick = { onActionClick() }) {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.body2,
                                color = MaterialTheme.colors.error
                            )
                        }
                    }
                }
            ) {
                Text(
                    text = data.message,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.surface
                )
            }
        }
    )
}