package com.example.myfitnessapp.presentation.shared.components

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfitnessapp.ui.theme.*


@Preview
@Composable
fun RegularButton(
    modifier: Modifier = Modifier,
    text: String = "button",
    textColor: Color = myWhite,
    backgroundColor: Color = myGreen,
    onClick: () -> Unit = {}
) {


    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(40.dp),
        modifier = modifier.clickable { onClick() },

        ) {

        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            ButtonText(
                text = text,
                color = textColor,
            )
        }


    }
}

@Composable
fun FloatingAddButton(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(70.dp)
            .background(myGreen)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = null,
            tint = myWhite,
            modifier = Modifier.size(40.dp)
        )
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RoundedCheckBox(
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit = {},
) {

    var color by remember { mutableStateOf(Color.Transparent) }
    var checkedState by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .size(30.dp)
            .clip(CircleShape)
            .clickable {
                checkedState = !checkedState
                onCheckedChange(checkedState)

                color = if (checkedState) {
                    myLightBlue
                } else {
                    Color.Transparent
                }
            }
            .border(BorderStroke(1.dp, myLightBlue), shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Surface(modifier = Modifier.fillMaxSize(), color = color) {
            AnimatedVisibility(
                visible = checkedState,
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut()
            ) {
                Icon(
                    imageVector = Icons.Rounded.CheckCircle,
                    contentDescription = null,
                    tint = myDarkBlue,
                    modifier = Modifier.size(40.dp)
                )
            }

        }


    }

}

@Composable
fun ButtonText(text: String, modifier: Modifier = Modifier, color: Color = myWhite) {
    Text(
        text = text,
        fontWeight = FontWeight.Light,
        fontFamily = quicksand,
        fontSize = 20.sp,
        color = color,
        modifier = modifier
    )
}
