package com.wajahatkarim3.droidcon.emea2020

import androidx.compose.animation.ColorPropKey
import androidx.compose.animation.core.*
import androidx.compose.animation.transition
import androidx.compose.foundation.Icon
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val fabSizeKey = FloatPropKey()
val fabColorKey = ColorPropKey()
val fabIconAlphaKey = FloatPropKey()

enum class FabSizeState {
    INITIAL, NORMAL, EXPLODED
}

@Composable
fun ExplodingFabButton() {
    val fabSizeState = remember { mutableStateOf(FabSizeState.NORMAL) }
    val secondaryColor = MaterialTheme.colors.secondary
    val primaryColor = MaterialTheme.colors.primary


    val fabTransitionDef = transitionDefinition<FabSizeState> {
        // What happens initially, this is used to prevent
        // transitioning to the next state until fabSizeState
        // becomes NORMAL or EXPLODED on click
        state(FabSizeState.INITIAL) {
            this[fabSizeKey] = 80f
            this[fabColorKey] = secondaryColor
            this[fabIconAlphaKey] = 1f
        }
        
        // What happens when Normal
        state(FabSizeState.NORMAL) {
            this[fabSizeKey] = 80f
            this[fabColorKey] = secondaryColor
            this[fabIconAlphaKey] = 1f
        }

        // What happens when Exploded
        state(FabSizeState.EXPLODED) {
            this[fabSizeKey] = 5000f
            this[fabColorKey] = primaryColor
            this[fabIconAlphaKey] = 0f
        }

        // Transition from Normal to Exploded
        transition(FabSizeState.NORMAL to FabSizeState.EXPLODED) {
            fabSizeKey using keyframes {
                durationMillis = 1000
                80f at 0
                35f at 200
                5000f at 1000
            }

            fabColorKey using tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        }

        // Transition from Exploded to Normal
        transition(FabSizeState.EXPLODED to FabSizeState.NORMAL) {
            fabSizeKey using tween(durationMillis = 1000, easing = FastOutSlowInEasing)
            fabColorKey using tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        }
    }

    val toState = if (fabSizeState.value == FabSizeState.NORMAL)
        FabSizeState.EXPLODED
    else FabSizeState.NORMAL

    val transitionState = transition(
        definition = fabTransitionDef,
        initState = fabSizeState.value,
        toState = toState
    )

    FabButton(fabSizeState = fabSizeState, transitionState = transitionState)
}

@Composable
fun FabButton(fabSizeState: MutableState<FabSizeState>, transitionState: TransitionState) {
    FloatingActionButton(
        onClick = {
            fabSizeState.value = if (fabSizeState.value == FabSizeState.NORMAL)
                FabSizeState.EXPLODED
            else FabSizeState.NORMAL
        },
        modifier = Modifier.size(transitionState[fabSizeKey].dp),
        backgroundColor = transitionState[fabColorKey]
    ) {
        Icon(asset = Icons.Default.Add,
            modifier = Modifier.drawOpacity(transitionState[fabIconAlphaKey])
        )
    }
}
