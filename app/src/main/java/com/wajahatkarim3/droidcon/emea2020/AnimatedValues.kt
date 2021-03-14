package com.wajahatkarim3.droidcon.emea2020

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.dp

@Composable
fun HeartBeatDemo() {
    val animScale = remember { Animatable(initialValue = 1f) }
    val animColor = remember { Animatable(initialValue = Color.Red) }

    LaunchedEffect(animScale) {
        animScale.animateTo(
            targetValue = 2f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 300, easing = FastOutLinearInEasing, delayMillis = 1000),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    LaunchedEffect(animColor) {
        animColor.animateTo(
            targetValue = Color.Blue,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 300, easing = LinearEasing, delayMillis = 1000),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    Image(imageVector = Icons.Default.Favorite,
        contentDescription = "Favourite Icon",
        modifier = Modifier.size((40*animScale.value).dp).padding(10.dp),
        colorFilter = ColorFilter.tint(animColor.value)
    )
}

@Composable
fun MovingSquare() {
    val animPosX = remember { Animatable(initialValue = 0f) }

    LaunchedEffect(animPosX) {
        animPosX.animateTo(
            targetValue = 500f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000)
            )
        )
    }

    Canvas(modifier = Modifier.size(100.dp), onDraw = {
        withTransform({
            translate(left = animPosX.value)
        }) {
            drawRect(color = Color.Red)
        }
    })
}