package com.buzigrowth.mathquest.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.buzigrowth.mathquest.components.GameSounds
import com.buzigrowth.mathquest.components.StagesGrid
import com.buzigrowth.mathquest.db.ProgressView
import com.buzigrowth.mathquest.ui.theme.MathQuestOrange

@Composable
fun Stages(modifier: Modifier= Modifier, pv: ProgressView= viewModel(),sounds: GameSounds){
    DisposableEffect(Unit) {
        onDispose {
            sounds.releasePool()

        }
    }
    val progress=pv.progress.collectAsState()
    Column(modifier = modifier.fillMaxSize().padding(15.dp)) {
        Row(modifier = Modifier.fillMaxWidth().padding(2.dp), horizontalArrangement = Arrangement.Center) {
            Text("Select Level", fontSize = 20.sp )
            Spacer(Modifier.weight(1f))
            Row(horizontalArrangement = Arrangement.Center) {
                Text(progress.value.sumOf { it.stars  }.toString())
                Icon(imageVector = Icons.Filled.StarRate, contentDescription = "StarRate", tint = MathQuestOrange)
            }

        }

        Spacer(Modifier.height(20.dp))

        StagesGrid(progress = progress,sounds)

    }
}