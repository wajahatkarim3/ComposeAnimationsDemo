package com.wajahatkarim3.droidcon.emea2020
//
//import androidx.compose.animation.core.*
//import androidx.compose.animation.transition
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Icon
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.Text
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CornerSize
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.Button
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.OutlinedButton
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Favorite
//import androidx.compose.material.icons.filled.FavoriteBorder
//import androidx.compose.material.icons.outlined.Favorite
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.drawOpacity
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.ColorFilter
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.unit.dp
//import androidx.ui.tooling.preview.Preview
//
//enum class RWButtonState {
//    IDLE, PRESSED
//}
//
//@Preview
//@Composable
//fun RayWenderlichFavButton() {
//    val buttonState = remember { mutableStateOf(RWButtonState.IDLE) }
//    val primaryColor = MaterialTheme.colors.primary
//
//    val transitionDef = transitionDefinition<RWButtonState> {
//        state(RWButtonState.IDLE) {
//            this[widthKey] = 200.dp
//            this[roundedCornerKey] = 6
//            this[backgroundColorKey] = Color.White
//            this[textColorKey] = primaryColor
//            this[textOpacityKey] = 1f
//            this[iconOpacityKey] = 0f
//        }
//        state(RWButtonState.PRESSED) {
//            this[widthKey] = 60.dp
//            this[roundedCornerKey] = 50
//            this[backgroundColorKey] = primaryColor
//            this[textColorKey] = Color.White
//            this[textOpacityKey] = 0f
//            this[iconOpacityKey] = 1f
//        }
//
//        transition(fromState = buttonState.value, toState = RWButtonState.PRESSED) {
//            widthKey using tween(durationMillis = 1000, easing = FastOutSlowInEasing)
//            roundedCornerKey using tween(durationMillis = 400)
//            backgroundColorKey using tween(durationMillis = 1000)
//            textColorKey using tween(durationMillis = 500)
//            textOpacityKey using tween(durationMillis = 1000)
//            iconOpacityKey using tween(durationMillis = 1000)
//        }
//
//        transition(RWButtonState.PRESSED to RWButtonState.IDLE) {
//            widthKey using tween(durationMillis = 1000, easing = FastOutSlowInEasing)
//            roundedCornerKey using tween(durationMillis = 400)
//            backgroundColorKey using tween(durationMillis = 1000)
//            textColorKey using tween(durationMillis = 500)
//            textOpacityKey using tween(durationMillis = 500)
//            iconOpacityKey using tween(durationMillis = 500)
//        }
//    }
//
//    val toState = if (buttonState.value == RWButtonState.IDLE) {
//        RWButtonState.PRESSED
//    } else
//        RWButtonState.IDLE
//
//    val stateTransition = transition(
//        definition = transitionDef,
//        initState = buttonState.value,
//        toState = toState
//    )
//
//    RWButton(buttonState = buttonState, transitionState = stateTransition)
//}
//
//@Composable
//fun RWButton(buttonState: MutableState<RWButtonState>, transitionState: TransitionState) {
//    Button(
//        onClick = {
//            buttonState.value = if (buttonState.value == RWButtonState.IDLE) {
//                RWButtonState.PRESSED
//            } else
//                RWButtonState.IDLE
//        },
//        border = BorderStroke(1.dp, color = MaterialTheme.colors.primary),
//        backgroundColor = transitionState[backgroundColorKey],
//        shape = RoundedCornerShape(corner = CornerSize(percent = transitionState[roundedCornerKey])),
//        modifier = Modifier.size(width = transitionState[widthKey], height = 60.dp)
//    ) {
//        if (buttonState.value == RWButtonState.PRESSED) {
//            Icon(
//                asset = Icons.Default.FavoriteBorder,
//                tint = transitionState[textColorKey]
//            )
//            Spacer(modifier = Modifier.padding(start = 5.dp))
//            Text(
//                text = "Add to Favourite",
//                style = TextStyle(
//                    color = transitionState[textColorKey]
//                ),
//                modifier = Modifier.drawOpacity(transitionState[textOpacityKey]),
//                softWrap = false
//            )
//        } else {
//            Icon(
//                asset = Icons.Default.Favorite,
//                tint = transitionState[textColorKey],
//                modifier = Modifier.drawOpacity(transitionState[iconOpacityKey])
//            )
//        }
//    }
//}