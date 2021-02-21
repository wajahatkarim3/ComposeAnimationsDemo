package com.wajahatkarim3.droidcon.emea2020

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class FabSizeState {
    NORMAL, EXPLODED
}

@Composable
fun ExplodingFabButton() {
    var fabSizeState by remember { mutableStateOf(FabSizeState.NORMAL) }
    val secondaryColor = MaterialTheme.colors.secondary
    val primaryColor = MaterialTheme.colors.primary


    val fabTransition: Transition<FabSizeState> = updateTransition(fabSizeState)

    val fabSize: Float by fabTransition.animateFloat(
        transitionSpec = {
            when {
                FabSizeState.NORMAL isTransitioningTo FabSizeState.EXPLODED -> {
                    keyframes {
                        durationMillis = 1000
                        80f at 0
                        35f at 200
                        5000f at 1000
                    }
                }
                FabSizeState.EXPLODED isTransitioningTo FabSizeState.NORMAL -> {
                    tween(durationMillis = 1000, easing = FastOutSlowInEasing)
                }
                else -> snap()
            }
        }
    ) { state ->
        when (state) {
            FabSizeState.NORMAL -> 80f
            FabSizeState.EXPLODED -> 5000f
        }
    }

    val fabColor: Color by fabTransition.animateColor(
        transitionSpec = {
            when {
                FabSizeState.NORMAL isTransitioningTo FabSizeState.EXPLODED -> {
                    tween(durationMillis = 1000, easing = FastOutSlowInEasing)
                }
                FabSizeState.EXPLODED isTransitioningTo FabSizeState.NORMAL -> {
                    tween(durationMillis = 1000, easing = FastOutSlowInEasing)
                }
                else -> snap()
            }
        }
    ) { state ->
        when (state) {
            FabSizeState.NORMAL -> secondaryColor
            FabSizeState.EXPLODED -> primaryColor
        }
    }

    val fabIconAlpha: Float by fabTransition.animateFloat() { state ->
        when (state) {
            FabSizeState.NORMAL -> 1f
            FabSizeState.EXPLODED -> 0f
        }
    }

    FloatingActionButton(
        onClick = {
            fabSizeState = if (fabSizeState == FabSizeState.NORMAL)
                FabSizeState.EXPLODED
            else FabSizeState.NORMAL
        },
        modifier = Modifier.size(fabSize.dp),
        backgroundColor = fabColor
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
            modifier = Modifier.alpha(fabIconAlpha)
        )
    }
}