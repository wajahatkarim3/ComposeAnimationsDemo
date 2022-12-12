package com.wajahatkarim3.animations.demo

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun AnimateContentSizeDemo() {
    Column(
        Modifier.wrapContentHeight()
            .padding(20.dp)
            .background(Color.Gray)
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        ExpandableText()
        Spacer(Modifier.height(20.dp))
        ExpandableButton()
        Spacer(Modifier.height(20.dp))
        PortraitModeImage()
        Spacer(Modifier.height(150.dp))
    }
}

@Composable
fun ExpandableText() {
    val shortText = "Click me"
    val longText = "Very long text passage that spans\nacross multiple lines, paragraphs\nand pages"
    var short by remember { mutableStateOf(true) }
    Box(
        modifier = Modifier
            .background(
                Color.Blue,
                RoundedCornerShape(15.dp)
            )
            .clickable { short = !short }
            .padding(20.dp)
            .wrapContentSize()
            .animateContentSize()
    ) {
        Text(
            if (short) {
                shortText
            } else {
                longText
            },
            style = TextStyle(color = Color.White)
        )
    }
}

@Composable
fun ExpandableButton() {
    val shortText = "Short"
    val longText = "Very loooooong text"
    var short by remember { mutableStateOf(true) }
    Button(
        { short = !short }
    ) {
        Text(
            if (short) {
                shortText
            } else {
                longText
            },
            style = TextStyle(color = Color.White),
            modifier = Modifier.animateContentSize()
        )
    }
}

@Composable
fun PortraitModeImage() {
    var portraitMode by remember { mutableStateOf(true) }
    Box(
        Modifier.clickable { portraitMode = !portraitMode }
            .sizeIn(maxWidth = 300.dp, maxHeight = 300.dp)
            .background(if (portraitMode) Color(0xFFfffbd0) else Color(0xFFe3ffd9))
            .animateContentSize(
                animationSpec = tween(500, easing = LinearEasing),
                finishedListener = { startSize, endSize ->
                    Log.d("droidcon", "$startSize -> $endSize")
                }
            )
            .aspectRatio(if (portraitMode) 3 / 4f else 16 / 9f)
    ) {
        Text(
            if (portraitMode) {
                "3 : 4"
            } else {
                "16 : 9"
            },
            style = TextStyle(color = Color.Black)
        )
    }
}
