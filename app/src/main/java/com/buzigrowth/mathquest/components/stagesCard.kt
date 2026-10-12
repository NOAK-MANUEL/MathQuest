package com.buzigrowth.mathquest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
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
fun StagesCard(
    stage: Int,
    progress: ProgressEntity?,
    completedCount: Int,
    sounds: GameSounds,
    modifier: Modifier = Modifier
) {
    val unlocked = progress != null || stage == completedCount + 1
    val stars = progress?.stars ?: 0
    val shape = RoundedCornerShape(12.dp)
    val navController = LocalNavController.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(shape)
            .then(
                if (unlocked) Modifier.background(
                    Brush.horizontalGradient(listOf(MathQuestPurple, MathQuestCyan))
                ) else Modifier.border(
                    1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f), shape
                )
            )
            .clickable {
                sounds.playTap()
                navController.navigate("game/$stage")
            }
            .padding(10.dp)
    ) {
        Text(
            text = stage.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.TopStart),
            color = if (unlocked) Color.White
            else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
        )

        if (unlocked) {
            Row(modifier = Modifier.align(Alignment.BottomCenter)) {
                repeat(3) { i ->
                    Icon(
                        imageVector = if (i < stars) Icons.Filled.StarRate else Icons.Filled.StarBorder,
                        contentDescription = null,
                        tint = if (i < stars) MathQuestOrange else Color.White.copy(alpha = 0.5f)
                    )
                }
            }
        } else {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = "Locked",
                modifier = Modifier.align(Alignment.BottomEnd).size(20.dp),
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
            )
        }
    }
}