package com.buzigrowth.mathquest.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buzigrowth.mathquest.ui.theme.MathQuestGreen

@Composable
fun AnswerCard(
    value: Int,
    isSelected: Int?,
    selectedStatus: Boolean?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(72.dp)
            .clickable(onClick = onClick, enabled = isSelected == null),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected == value) {
                if (selectedStatus != null && selectedStatus ){
                    MathQuestGreen
                }else Color.Red
            }else MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(value.toString(), fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}
