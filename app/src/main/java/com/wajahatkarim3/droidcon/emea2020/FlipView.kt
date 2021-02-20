package com.wajahatkarim3.droidcon.emea2020
//
//import androidx.compose.animation.core.keyframes
//import androidx.compose.animation.core.transitionDefinition
//import androidx.compose.animation.transition
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.alpha
//import androidx.compose.ui.graphics.graphicsLayer
//
//enum class FlipViewState {
//    FRONT, BACK
//}
//
//@Composable
//fun FlipView(front: @Composable () -> Unit, back: @Composable () -> Unit, flipDurationMs: Int = 1000) {
//
//    val flipViewState = remember { mutableStateOf(FlipViewState.FRONT) }
//
//    val rotationTranDef = transitionDefinition<FlipViewState> {
//        state(FlipViewState.FRONT) {
//            this[frontRotationKey] = 0f
//            this[backRotationKey] = 180f
//            this[frontOpacityKey] = 1f
//            this[backOpacityKey] = 0f
//        }
//
//        state(FlipViewState.BACK) {
//            this[frontRotationKey] = 180f
//            this[backRotationKey] = 0f
//            this[frontOpacityKey] = 0f
//            this[backOpacityKey] = 1f
//        }
//
//        transition(fromState = FlipViewState.FRONT, toState = FlipViewState.BACK) {
//            frontRotationKey using keyframes {
//                durationMillis = flipDurationMs
//                0f at 0
//                90f at flipDurationMs / 2
//                90f at flipDurationMs
//            }
//            frontOpacityKey using keyframes {
//                durationMillis = flipDurationMs
//                1f at 0
//                1f at (flipDurationMs / 2) - 1
//                0f at flipDurationMs / 2
//                0f at flipDurationMs
//            }
//
//            backRotationKey using keyframes {
//                durationMillis = flipDurationMs
//                -90f at 0
//                -90f at flipDurationMs / 2
//                0f at flipDurationMs
//            }
//            backOpacityKey using keyframes {
//                durationMillis = flipDurationMs
//                0f at 0
//                0f at (flipDurationMs / 2) - 1
//                1f at flipDurationMs / 2
//                1f at flipDurationMs
//            }
//        }
//
//        transition(FlipViewState.BACK to FlipViewState.FRONT) {
//            frontRotationKey using keyframes {
//                durationMillis = flipDurationMs
//                90f at 0
//                90f at flipDurationMs / 2
//                0f at flipDurationMs
//            }
//            frontOpacityKey using keyframes {
//                durationMillis = flipDurationMs
//                0f at 0
//                0f at (flipDurationMs / 2) - 1
//                1f at flipDurationMs / 2
//                1f at flipDurationMs
//            }
//
//            backRotationKey using keyframes {
//                durationMillis = flipDurationMs
//                0f at 0
//                -90f at flipDurationMs / 2
//                -90f at flipDurationMs
//            }
//            backOpacityKey using keyframes {
//                durationMillis = flipDurationMs
//                1f at 0
//                1f at (flipDurationMs / 2) - 1
//                0f at flipDurationMs / 2
//                0f at flipDurationMs
//            }
//        }
//    }
//
//    val toState = if (flipViewState.value == FlipViewState.FRONT) FlipViewState.BACK else FlipViewState.FRONT
//
//    val flipTransitionState = transition(
//        definition = rotationTranDef,
//        initState = flipViewState.value,
//        toState = toState
//    )
//
//    Box(
//        modifier = Modifier.fillMaxSize().clickable(onClick = {
//            flipViewState.value = if (flipViewState.value == FlipViewState.FRONT) FlipViewState.BACK else FlipViewState.FRONT
//        }),
//        contentAlignment = Alignment.Center,
//    ) {
//        Box(modifier = Modifier.graphicsLayer(
//            rotationY = flipTransitionState[backRotationKey]
//        ).alpha(flipTransitionState[backOpacityKey])) {
//            back()
//        }
//        Box(modifier = Modifier.graphicsLayer(
//            rotationY = flipTransitionState[frontRotationKey]
//        ).alpha(flipTransitionState[frontOpacityKey])) {
//            front()
//        }
//    }
//}