package com.buzigrowth.mathquest.layouts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.buzigrowth.mathquest.components.GameSounds
import com.buzigrowth.mathquest.properties.LocalNavController
import com.buzigrowth.mathquest.screens.Game
import com.buzigrowth.mathquest.screens.Home
import com.buzigrowth.mathquest.screens.StageComplete
import com.buzigrowth.mathquest.screens.Stages

@Composable
fun AppLayout(){
    val navController = rememberNavController()
    val context = LocalContext.current
    val sounds = remember{ GameSounds(context) }
    DisposableEffect(Unit) {
        sounds.startMusic()
        onDispose { sounds.release() }
    }
    CompositionLocalProvider(
        LocalNavController provides navController
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            NavHost(navController, startDestination = "complete/1", modifier = Modifier.fillMaxSize()) {
                composable("home") {
                    Home(modifier = Modifier.padding(innerPadding), sounds = sounds)
                }
                composable ("stages"){
                    Stages(sounds = sounds,modifier = Modifier.padding(innerPadding))
                }
                composable ("game/{level}",
                    arguments = listOf(navArgument("level"){type= NavType.IntType})
                    ){params->
                    val level = params.arguments?.getInt("level")?:1
                    Game(sounds=sounds, modifier = Modifier.padding(innerPadding),level)
                }

                composable("complete/{level}", arguments = listOf(navArgument("level"){ NavType.IntType})) { params->
                    val level = params.arguments?.getInt("level")?:1
                    StageComplete(modifier = Modifier.padding(innerPadding), level = level)
                }
            }
        }
    }
}