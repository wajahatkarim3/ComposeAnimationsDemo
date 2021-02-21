package com.wajahatkarim3.droidcon.emea2020

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.animation.animateColor
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

enum class RWButtonState {
    IDLE, PRESSED
}

@Composable
fun RayWenderlichFavButton() {
    var toState by remember { mutableStateOf(RWButtonState.IDLE) }
    val primaryColor = MaterialTheme.colors.primary

    val transition: Transition<RWButtonState> = updateTransition(targetState = toState)

    val width: Dp by transition.animateDp(
        transitionSpec = {
            when {
                RWButtonState.IDLE isTransitioningTo RWButtonState.PRESSED -> {
                    tween(durationMillis = 1000, easing = FastOutSlowInEasing)
                }
                RWButtonState.PRESSED isTransitioningTo RWButtonState.IDLE -> {
                    tween(durationMillis = 1000, easing = FastOutSlowInEasing)
                }
                else -> tween(durationMillis = 1000, easing = FastOutSlowInEasing)
            }
        }
    ) { state ->
        when(state) {
            RWButtonState.IDLE -> 200.dp
            RWButtonState.PRESSED -> 60.dp
        }
    }

    val roundedCorner: Int by transition.animateInt(
        transitionSpec = {
            when {
                RWButtonState.IDLE isTransitioningTo RWButtonState.PRESSED -> {
                    tween(durationMillis = 400)
                }
                RWButtonState.PRESSED isTransitioningTo RWButtonState.IDLE -> {
                    tween(durationMillis = 400)
                }
                else -> tween(durationMillis = 400)
            }
        }
    ) { state ->
        when(state) {
            RWButtonState.IDLE -> 6
            RWButtonState.PRESSED -> 50
        }
    }

    val backgroundColor: Color by transition.animateColor(
        transitionSpec = {
            when {
                RWButtonState.IDLE isTransitioningTo RWButtonState.PRESSED -> {
                    tween(durationMillis = 1000)
                }
                RWButtonState.PRESSED isTransitioningTo RWButtonState.IDLE -> {
                    tween(durationMillis = 1000)
                }
                else -> tween(durationMillis = 1000)
            }
        }
    ) { state ->
        when(state) {
            RWButtonState.IDLE -> Color.White
            RWButtonState.PRESSED -> primaryColor
        }
    }

    val textColor: Color by transition.animateColor(
        transitionSpec = {
            when {
                RWButtonState.IDLE isTransitioningTo RWButtonState.PRESSED -> {
                    tween(durationMillis = 500)
                }
                RWButtonState.PRESSED isTransitioningTo RWButtonState.IDLE -> {
                    tween(durationMillis = 500)
                }
                else -> tween(durationMillis = 500)
            }
        }
    ) { state ->
        when(state) {
            RWButtonState.IDLE -> primaryColor
            RWButtonState.PRESSED -> Color.White
        }
    }

    val textOpacity: Float by transition.animateFloat(
        transitionSpec = {
            when {
                RWButtonState.IDLE isTransitioningTo RWButtonState.PRESSED -> {
                    tween(durationMillis = 1000)
                }
                RWButtonState.PRESSED isTransitioningTo RWButtonState.IDLE -> {
                    tween(durationMillis = 1000)
                }
                else -> tween(durationMillis = 1000)
            }
        }
    ) { state ->
        when(state) {
            RWButtonState.IDLE -> 1f
            RWButtonState.PRESSED -> 0f
        }
    }

    Button(
        onClick = {
              toState = if (toState == RWButtonState.PRESSED) RWButtonState.IDLE else RWButtonState.PRESSED
        },
        border = BorderStroke(1.dp, color = MaterialTheme.colors.primary),
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor, contentColor = textColor),
        shape = RoundedCornerShape(corner = CornerSize(percent = roundedCorner)),
        modifier = Modifier.size(width = width, height = 60.dp)
    ) {
        Icon(
            imageVector = if (toState == RWButtonState.IDLE) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
            contentDescription = "Favorite",
            tint = textColor
        )
        Spacer(modifier = Modifier.padding(start = 5.dp))
        Text(
            text = "Add to Favourite",
            style = TextStyle(
                color = primaryColor
            ),
            modifier = Modifier.alpha(textOpacity),
            softWrap = false
        )
    }
}