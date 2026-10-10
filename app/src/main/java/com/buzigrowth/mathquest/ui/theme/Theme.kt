package com.buzigrowth.mathquest.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = MathQuestBlue,
    secondary = MathQuestPurple,
    tertiary = MathQuestYellow,


            background = MathQuestDeepBlue ,
     surface  = Color(0xFF11183D),

 onPrimary = Color.White,
 onSecondary = Color.White,
onTertiary = Color(0xFF211600),

 onBackground = MathQuestWhite,
 onSurface = MathQuestWhite
)



@Composable
fun MathQuestTheme(
    content: @Composable () -> Unit
) {


    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}