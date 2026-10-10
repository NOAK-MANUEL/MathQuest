package com.buzigrowth.mathquest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.buzigrowth.mathquest.layouts.AppLayout

import com.buzigrowth.mathquest.ui.theme.MathQuestTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        enableEdgeToEdge()
        setContent {
            MathQuestTheme {
                AppLayout()
            }
        }
    }
}
