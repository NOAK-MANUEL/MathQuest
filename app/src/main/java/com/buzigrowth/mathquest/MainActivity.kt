package com.buzigrowth.mathquest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.buzigrowth.mathquest.components.GameSounds

import com.buzigrowth.mathquest.layouts.AppLayout

import com.buzigrowth.mathquest.ui.theme.MathQuestTheme

class MainActivity : ComponentActivity() {
    private lateinit var sounds: GameSounds
    override fun onPause() {
        sounds.pauseMusic()
        super.onPause()
    }

    override fun onResume() {
        sounds.startMusic()
        super.onResume()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sounds = GameSounds(applicationContext)


        enableEdgeToEdge()
        setContent {
            MathQuestTheme {
                AppLayout(sounds=sounds)
            }
        }
    }
}
