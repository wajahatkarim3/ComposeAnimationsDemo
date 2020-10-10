package com.wajahatkarim3.droidcon.emea2020

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animate
import androidx.compose.foundation.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.TransformOrigin
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.drawLayer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.unit.dp
import java.util.*

@ExperimentalAnimationApi
@Composable
fun CircleMenu() {
    val menuOpen = remember { mutableStateOf(false) }
    var distance = 150f
    var startDegrees = -90.0

    var menuOptions = ArrayList<MenuOptionModel>()
    menuOptions.add(MenuOptionModel(icon = Icons.Default.Home, backgroundColor = Color(0xFF35ACE9)))
    menuOptions.add(MenuOptionModel(icon = Icons.Default.Search, backgroundColor = Color(0xFF648C00)))
    menuOptions.add(MenuOptionModel(icon = Icons.Default.Notifications, backgroundColor = Color(0xFFFF5242)))
    menuOptions.add(MenuOptionModel(icon = Icons.Default.Settings, backgroundColor = Color(0xFFAD71D2)))
    menuOptions.add(MenuOptionModel(icon = Icons.Default.LocationOn, backgroundColor = Color(0xFFFFBD2E)))

    var degreeDiff = 360 / menuOptions.size

    Box(modifier = Modifier.fillMaxWidth().preferredHeight(500.dp),
        alignment = Alignment.Center
    ) {
        // Menu Center Icon
        Icon(
            asset = if (menuOpen.value) Icons.Default.Close else Icons.Default.Menu,
            modifier = Modifier.clickable(onClick = {
                menuOpen.value = !menuOpen.value
            }, indication = null).background(
                    color = if (menuOpen.value) Color.Gray else Color.White,
                    shape = CircleShape)
                .padding(10.dp),
            tint = Color.Black
        )

        // Surrounding Menu Options
        var degrees = startDegrees
        for (i in 0 until menuOptions.size) {
            var menu = menuOptions[i]
            MenuIcon(
                icon = menu.icon,
                backgroundColor = menu.backgroundColor,
                degrees = degrees,
                distance = distance,
                menuOpen.value
            )
            degrees += degreeDiff
        }

    }
}

@Composable
fun MenuIcon(icon: VectorAsset, backgroundColor: Color, degrees: Double, distance: Float, isMenuOpen: Boolean) {
    // 0
    Icon(
        asset = icon,
        modifier = Modifier
            .drawLayer(
                translationX = animate(
                    if (isMenuOpen) distance * Math.cos(Math.toRadians(degrees)).toFloat() else 0f
                ),
                translationY = animate(
                    if (isMenuOpen) distance * Math.sin(Math.toRadians(degrees)).toFloat() else 0f
                )
            )
            .drawOpacity(animate(target = if (isMenuOpen) 1f else 0f))
            .background(color = backgroundColor, shape = CircleShape).padding(10.dp),
        tint = Color.White
    )
}

data class MenuOptionModel (
    val icon: VectorAsset,
    val backgroundColor: Color
)