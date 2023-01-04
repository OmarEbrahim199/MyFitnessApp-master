package com.example.myfitnessapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.myfitnessapp.presentation.shared.components.animation.LoadingAnimation
import com.example.myfitnessapp.presentation.shared.components.recipes.DefaultSnackbar
import com.example.myfitnessapp.presentation.theme.*

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun MyFitnessAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}


private val LightThemeColors = lightColors(
    primary = Primary,
    primaryVariant = PrimaryVariant,
    onPrimary = White,
    secondary = Secondary,
    secondaryVariant = SecondaryVariant,
    onSecondary = Black,
    error = ErrorRedDark,
    onError = White,
    background = FaintGray,
    onBackground = Black,
    surface = White,
    onSurface = Black
)

private val DarkThemeColors = darkColors(
    primary = Pink300,
    primaryVariant = PrimaryVariant,
    onPrimary = Black,
    secondary = Secondary,
    secondaryVariant = SecondaryVariant,
    onSecondary = Black,
    error = ErrorRedLight,
    onError = Black,
    background = JustBlack,
    onBackground = White,
    surface = JustBlack,
    onSurface = White
)

@Composable
fun AppTheme(
    isProgressBarShowing: Boolean,
    scaffoldState: ScaffoldState,
    snackbarAlignment: Alignment,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors =  LightThemeColors,
        typography = QuickSandTypography,
        shapes = Shapes
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = FaintGray
                )
        ) {
            content()
            //CircularIndeterminateProgressBar(isProgressBarShowing, 0.7f)

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoadingAnimation(isProgressBarShowing)
            }

            DefaultSnackbar(
                snackbarHostState = scaffoldState.snackbarHostState,
                modifier = Modifier.align(snackbarAlignment)
            ) {
                scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
            }
        }
    }
}