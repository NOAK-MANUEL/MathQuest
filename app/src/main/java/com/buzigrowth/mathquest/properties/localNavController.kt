package com.buzigrowth.mathquest.properties

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController

val LocalNavController =
    staticCompositionLocalOf<NavHostController> {
        error("NavController not provided")
    }