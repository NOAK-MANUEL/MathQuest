package com.buzigrowth.mathquest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.buzigrowth.mathquest.db.ProgressEntity
import com.buzigrowth.mathquest.db.ProgressView
import com.buzigrowth.mathquest.properties.LocalNavController
import com.buzigrowth.mathquest.ui.theme.MathQuestCyan
import com.buzigrowth.mathquest.ui.theme.MathQuestOrange
import com.buzigrowth.mathquest.ui.theme.MathQuestPurple

@Composable
fun StagesCard(stage: Int,progress: ProgressEntity?,size:Int,sounds: GameSounds){

    val isCurrent = progress != null || (size+1) == stage

    val shape = RoundedCornerShape(12.dp)
    val bg = if(isCurrent)  Brush.horizontalGradient(
        colors = listOf(
            MathQuestPurple,
            MathQuestCyan
        )
    )else SolidColor(Color.Transparent)
    val navController = LocalNavController.current
    Box( modifier = Modifier.fillMaxWidth().aspectRatio(1f).clickable(enabled = isCurrent,onClick = {
        sounds.playTap()
        navController.navigate("game/$stage")}).background(
        brush = bg,
        shape=shape
    )) {
        Column(modifier = Modifier.padding(10.dp).fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Text(stage.toString(), fontSize = 20.sp)
            Spacer(Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(progress?.stars ?: 0){
                    Icon(
                        imageVector = Icons.Filled.StarRate,
                        contentDescription = "rate",
                        tint = MathQuestOrange
                    )
                }

//
            }
        }
    }
}