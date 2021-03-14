package com.wajahatkarim3.droidcon.emea2020

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.material.ButtonDefaults

@Composable
fun ColorAnimation() {
    val enabled = remember { mutableStateOf(true) }
    val color: Color by animateColorAsState(
        if (enabled.value) MaterialTheme.colors.primary else MaterialTheme.colors.secondary
    )
    Button(
        onClick = { enabled.value = !enabled.value },
        colors = ButtonDefaults.buttonColors(backgroundColor = color),
        modifier = Modifier.padding(16.dp).fillMaxWidth()
    ) {
        Text("Color Animation")
    }
}

@Composable
fun ScaleAndColorAnimation() {
    val enabled = remember { mutableStateOf(true) }
    val color: Color by animateColorAsState(
        if (enabled.value) MaterialTheme.colors.primary else MaterialTheme.colors.secondary)
    val height: Dp by animateDpAsState(if (enabled.value) 40.dp else 60.dp)
    val width: Dp by animateDpAsState(if (enabled.value) 150.dp else 300.dp)
    Button(
        onClick = { enabled.value = !enabled.value },
        colors = ButtonDefaults.buttonColors(backgroundColor = color),
        modifier = Modifier
            .padding(16.dp)
            .height(height)
            .width(width),
    ) {
        Text("Scale & Color")
    }
}

@Composable
fun GenderSelectAnimation() {
    val female = remember { mutableStateOf(true) }
    Row(horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.male),
            contentDescription = "Male Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(animateDpAsState(if (female.value) 100.dp else 250.dp).value)
                .border(width = animateDpAsState(if (female.value) 0.dp else 4.dp).value,
                    color = animateColorAsState(if (female.value) Color.Transparent else Color.Red).value
                )
                .padding(8.dp)
                .clickable { female.value = !female.value }
                .clip(RoundedCornerShape(animateDpAsState(if (female.value) 0.dp else 8.dp).value))
        )
        Image(
            painter = painterResource(R.drawable.female),
            contentDescription = "Female Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(animateDpAsState(if (!female.value) 100.dp else 250.dp).value)
                .border(width = animateDpAsState(if (!female.value) 0.dp else 4.dp).value,
                    color = animateColorAsState(if (!female.value) Color.Transparent else Color.Red).value)
                .padding(8.dp)
                .clickable { female.value = !female.value }
                .clip(RoundedCornerShape(animateDpAsState(if (!female.value) 0.dp else 8.dp).value))
        )
    }
}