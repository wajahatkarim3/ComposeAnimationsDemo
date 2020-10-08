package com.wajahatkarim3.droidcon.emea2020

import androidx.compose.animation.animatedColor
import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onActive
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.drawLayer
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeartBeatDemo() {
    val animScale = animatedFloat(initVal = 1f)
    val animColor = animatedColor(initVal = Color.Red)

    onActive {
        animScale.animateTo(
            targetValue = 1.3f,
            anim = repeatable(
                iterations = AnimationConstants.Infinite,
                animation = tween(durationMillis = 300, easing = FastOutLinearInEasing, delayMillis = 1000)
            )
        )

        animColor.animateTo(
            targetValue = Color.Blue,
            anim = repeatable(
                iterations = AnimationConstants.Infinite,
                animation = tween(durationMillis = 300, easing = LinearEasing, delayMillis = 1000)
            )
        )
    }

    Image(asset = Icons.Default.Favorite,
        modifier = Modifier.size((40*animScale.value).dp).padding(10.dp),
        colorFilter = ColorFilter.tint(animColor.value)
    )

}

@Composable
fun MovingSquare() {
    val animPosX = animatedFloat(initVal = 0f)
    onActive {
        animPosX.animateTo(
            targetValue = 500f,
            anim = repeatable(
                iterations = AnimationConstants.Infinite,
                animation = tween(durationMillis = 1000)
            )
        )
    }

    Canvas(modifier = Modifier.preferredSize(100.dp), onDraw = {
        withTransform({
            translate(left = animPosX.value)
        }) {
            drawRect(color = Color.Red)
        }
    })
}
