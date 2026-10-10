package com.buzigrowth.mathquest.functions

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun shake(scope: CoroutineScope,offset:  Animatable<Float, AnimationVector1D>){
    scope.launch {
        offset.animateTo(-10f)
        offset.animateTo(10f)
        offset.animateTo(-8f)
        offset.animateTo(8f)
        offset.animateTo(0f)
    }
}