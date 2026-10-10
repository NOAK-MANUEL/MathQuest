package com.buzigrowth.mathquest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.LocalBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.buzigrowth.mathquest.ui.theme.MathQuestCyan
import com.buzigrowth.mathquest.ui.theme.MathQuestOrange
import com.buzigrowth.mathquest.ui.theme.MathQuestPurple
import com.buzigrowth.mathquest.ui.theme.MathQuestWhite

@Composable
fun StageComplete(modifier: Modifier= Modifier, level:Int){
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
        Icon(imageVector = Icons.Filled.LocalBar, contentDescription = "cup", tint = MathQuestOrange, modifier = Modifier
            .size(30.dp)
            .background(
                color = MathQuestOrange,

                ))

        Text("Level $level Complete")

        Card() {
            Column() {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(imageVector = Icons.Filled.Alarm, contentDescription = "alarm")
                    Text("Timer")

                    Spacer(Modifier.weight(1f))

                    Text("20:20")
                }
            }
        }

        Box(modifier = Modifier.background(
            brush = Brush.horizontalGradient(
                colors = listOf(
                    MathQuestPurple,
                    MathQuestCyan
                )
            )
        )) {
            Button(onClick = {}, modifier = Modifier.padding(20.dp)) {
                Text("Next Level")
            }
        }
    }
}