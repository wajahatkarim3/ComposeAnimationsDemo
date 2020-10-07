package com.wajahatkarim3.droidcon.emea2020

import androidx.compose.animation.animate
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp

@Composable
fun ColorAnimation() {
    val enabled = remember { mutableStateOf(true) }
    val color = if (enabled.value) MaterialTheme.colors.primary else MaterialTheme.colors.secondary
    Button(
        onClick = { enabled.value = !enabled.value },
        backgroundColor = animate(color),
        modifier = Modifier.padding(16.dp).fillMaxWidth()
    ) {
        Text("Color Animation")
    }
}

@Composable
fun ScaleAndColorAnimation() {
    val enabled = remember { mutableStateOf(true) }
    val color = if (enabled.value) MaterialTheme.colors.primary else MaterialTheme.colors.secondary
    val height = if (enabled.value) 40.dp else 60.dp
    val width = if (enabled.value) 150.dp else 300.dp
    Button(
        onClick = { enabled.value = !enabled.value },
        backgroundColor = animate(color),
        modifier = Modifier
            .padding(16.dp)
            .preferredHeight(animate(height))
            .preferredWidth(animate(width)),
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
            asset = imageResource(R.drawable.male),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .preferredSize(animate(if (female.value) 100.dp else 250.dp))
                .border(width = animate(if (female.value) 0.dp else 4.dp),
                    color = animate(if (female.value) Color.Transparent else Color.Red))
                .padding(8.dp)
                .clickable { female.value = !female.value }
                .clip(RoundedCornerShape(animate(if (female.value) 0.dp else 8.dp)))
        )
        Image(
            asset = imageResource(R.drawable.female),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .preferredSize(animate(if (!female.value) 100.dp else 250.dp))
                .border(width = animate(if (!female.value) 0.dp else 4.dp),
                    color = animate(if (!female.value) Color.Transparent else Color.Red))
                .padding(8.dp)
                .clickable { female.value = !female.value }
                .clip(RoundedCornerShape(animate(if (!female.value) 0.dp else 8.dp)))
        )
    }
}