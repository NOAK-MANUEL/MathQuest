package com.buzigrowth.mathquest.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.buzigrowth.mathquest.db.ProgressEntity

@Composable
fun StagesGrid( progress: State<List<ProgressEntity>>,sounds: GameSounds){

    LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(12.dp), horizontalArrangement = Arrangement.spacedBy(6.dp) ) {
        items(30){index->
            StagesCard(index+1, sounds = sounds ,progress = progress.value.find { it.level==index+1 }, size = progress.value.size)

        }
    }
}