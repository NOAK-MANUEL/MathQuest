package com.buzigrowth.mathquest.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buzigrowth.mathquest.R
import com.buzigrowth.mathquest.components.GameSounds
import com.buzigrowth.mathquest.properties.LocalNavController
import com.buzigrowth.mathquest.ui.theme.MathQuestCyan
import com.buzigrowth.mathquest.ui.theme.MathQuestPurple
import com.buzigrowth.mathquest.ui.theme.MathQuestTextSecondary
import com.buzigrowth.mathquest.ui.theme.MathQuestWhite

@Composable
fun Home(sounds: GameSounds,modifier: Modifier= Modifier){
    DisposableEffect(Unit) {
        onDispose {
            sounds.releasePool()

        }
    }
    val navController = LocalNavController.current
    Column (modifier=modifier
        .fillMaxSize()
        .padding(20.dp)
      , verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(R.drawable.math_quest_logo), contentDescription = "Logo", modifier = Modifier.size(120.dp))

        Spacer(modifier = Modifier.height(10.dp))

        Row() {
            Text("Math", fontSize = 40.sp, fontWeight = FontWeight.Bold)
            Text("Quest", fontSize = 40.sp, color = MathQuestPurple,  fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(modifier= Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Text("Think  .", fontWeight = FontWeight.Bold, color = MathQuestTextSecondary)
            Text("Solve  .",fontWeight = FontWeight.Bold, color = MathQuestTextSecondary)
            Text("Grow",fontWeight = FontWeight.Bold, color = MathQuestTextSecondary)
        }
        Spacer(modifier = Modifier.height(70.dp))


            Box (modifier = Modifier.fillMaxWidth().clickable(onClick = {
                sounds.playTap()
                navController.navigate("stages")}).background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        MathQuestPurple,
                        MathQuestCyan
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            ),){
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = "Play",
                        tint = MathQuestWhite
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        "Play",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MathQuestWhite
                    )
                }
            }


        Spacer(modifier = Modifier.height(20.dp))


        Row(horizontalArrangement = Arrangement.SpaceBetween) {

            Column(verticalArrangement = Arrangement.SpaceBetween) {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.QueryStats, contentDescription = "Stats")
                }

                Text("Stats")
            }

            Column(verticalArrangement = Arrangement.SpaceBetween) {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Info, contentDescription = "Info")
                }
                Text("About")

            }
        }

    }
}