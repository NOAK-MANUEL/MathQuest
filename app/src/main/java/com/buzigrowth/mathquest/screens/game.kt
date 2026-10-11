package com.buzigrowth.mathquest.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.buzigrowth.mathquest.components.AnswerCard
import com.buzigrowth.mathquest.components.GameSounds
import com.buzigrowth.mathquest.db.ProgressView
import com.buzigrowth.mathquest.functions.Question
import com.buzigrowth.mathquest.functions.generateQuestion
import com.buzigrowth.mathquest.functions.shake
import com.buzigrowth.mathquest.properties.LocalNavController
import com.buzigrowth.mathquest.ui.theme.MathQuestCyan
import com.buzigrowth.mathquest.ui.theme.MathQuestGreen
import kotlinx.coroutines.delay
import kotlin.math.roundToInt
import kotlin.time.Duration.Companion.milliseconds

private const val TOTAL_QUESTIONS = 15

@Composable
fun Game(sounds: GameSounds, modifier: Modifier = Modifier, level: Int, pv: ProgressView= viewModel()) {
    val navigate = LocalNavController.current
    val scope = rememberCoroutineScope()
    val offsetX = remember { Animatable(0f) }

    val seconds = remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true){
            delay(1000.milliseconds)
            seconds.intValue++
        }

    }

    // Real state that survives recomposition
    val questionsAsked = remember { mutableStateListOf<Question>() }
    val userAnswers = remember { mutableStateListOf<UserAnswer>() }

    fun nextUniqueQuestion(): Question {
        var q = generateQuestion(level)
        var attempts = 0
        while (q in questionsAsked && attempts++ < 50) {   // guard against infinite loop
            q = generateQuestion(level)
        }
        return q
    }

    // Seed the first question once
    if (questionsAsked.isEmpty()) {
        questionsAsked.add(nextUniqueQuestion())
    }

    val question = questionsAsked.last()

    // Reset per-question state whenever the question changes
    val options = remember(question) { generateOption(question.answer) }
    val selected = remember(question) { mutableStateOf<Int?>(null) }
    val isCorrect = selected.value == question.answer

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text("Level $level", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text("Question ${questionsAsked.size} of $TOTAL_QUESTIONS", style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(Modifier.weight(1f))
            Icon(Icons.Filled.Timer, contentDescription = "clock")
            Spacer(Modifier.width(4.dp))
            Text("%02d:%02d".format(seconds.intValue/60,seconds.intValue%60), style = MaterialTheme.typography.titleMedium)
        }

        // Question
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = 24.dp)
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("${question.text} = ?", fontSize = 40.sp, fontWeight = FontWeight.Bold)
            }
        }

        // Options
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            options.chunked(2).forEach { rowItems ->
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    rowItems.forEach { value ->
                        AnswerCard (
                            value = value,
                            isSelected = selected.value,
                            selectedStatus = isCorrect,
                            modifier = Modifier
                                .weight(1f)
                                .offset {
                                    // shake only the wrong card
                                    IntOffset(offsetX.value.roundToInt(), 0)
                                },
                            onClick = {
                                selected.value = value
                                userAnswers.add(UserAnswer(answer = value, correct = question.answer == value))
                                if (value != question.answer){
                                    sounds.playWrong()
                                    shake(scope, offsetX)
                                }else{
                                    sounds.playCorrect()
                                }
                            }
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // Buttons
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedButton(
                onClick = {
                    sounds.playTap()
                    navigate.popBackStack() },
                modifier = Modifier.height(20.dp)
            ) { Text("Quit") }

            ElevatedButton(
                onClick = {
                    sounds.playTap()
                    if (questionsAsked.size < TOTAL_QUESTIONS) {
                        questionsAsked.add(nextUniqueQuestion())
                    } else {
                        sounds.playComplete()
                        var correctCount =0
                        userAnswers.forEach { ans->
                            if (ans.correct) correctCount++
                        }
                        val stars = if (correctCount<5) 0 else (correctCount/5)
                        pv.completeLevel(level = level, stars = stars, numberGot = correctCount, numberFailed = userAnswers.size-correctCount, completed = stars !=0 ,timeUsage = seconds.intValue)
                        if (stars==0){
                            navigate.navigate("fail/$level",{
                                popUpTo("game/$level") { inclusive=true }
                            })

                        }else{
                            navigate.navigate("complete/$level",{
                                popUpTo("game/${level}") {inclusive=true  }
                            })

                        }
                    }
                },
                enabled = selected.value != null,   // only advance after a correct answer
                modifier = Modifier.height(20.dp)
            ) {
                Text(if (questionsAsked.size < TOTAL_QUESTIONS) "Next" else "Finish")
            }
        }
    }
}

private fun generateOption(correct:Int): List<Int>{
    var options = listOf(correct)
    while(options.size <4) {
        val random = Math.random() * (correct+3+Math.random())
        val value = random.toInt()
        if (!options.contains(value)){
            options = options+value
        }

    }

    return options.shuffled()
}

private data class UserAnswer(
    val answer: Int,
    val correct: Boolean
)