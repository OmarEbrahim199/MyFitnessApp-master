package com.example.myfitnessapp.presentation.shared.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfitnessapp.ui.theme.myDarkBlue
import com.example.myfitnessapp.ui.theme.myGreen

@Preview
@Composable
fun CommandsDisplay(
    modifier: Modifier = Modifier,
    iconStart: ImageVector = Icons.Rounded.ArrowBack,
    iconStartClick: () -> Unit = {},
    iconCenter: ImageVector = Icons.Rounded.PlayArrow,
    iconCenterClick: () -> Unit = {},
    iconEnd: ImageVector = Icons.Rounded.ArrowForward,
    iconEndClick: () -> Unit = {},
    isWorkoutDay:Boolean = true

    ) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Surface(
            shape = CircleShape, modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .align(Alignment.Top)
                .clickable { iconStartClick() },
            elevation = 4.dp,
            color = myGreen
        ) {
            Icon(
                imageVector = iconStart,
                contentDescription = null,
                tint = myDarkBlue,
                modifier = Modifier.padding(15.dp)
            )
        }

        if (isWorkoutDay)
        {
            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .width(95.dp)
                    .height(95.dp)
                    .clickable { iconCenterClick() },
                elevation = 4.dp,
                color = myGreen
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {

                    Icon(
                        imageVector = iconCenter,
                        contentDescription = null,
                        tint = myDarkBlue,
                        modifier = Modifier.size(50.dp)
                    )
                }

            }
        } else { Spacer(modifier = Modifier.size(95.dp))}

        Surface(
            shape = CircleShape, modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .align(Alignment.Top)
                .clickable { iconEndClick() },
            elevation = 4.dp,
            color = myGreen
        ) {

            Icon(
                imageVector = iconEnd,
                contentDescription = null,
                tint = myDarkBlue,
                modifier = Modifier.padding(15.dp)
            )
        }

    }

}