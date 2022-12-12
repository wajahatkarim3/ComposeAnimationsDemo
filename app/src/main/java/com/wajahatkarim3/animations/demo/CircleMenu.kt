package com.wajahatkarim3.animations.demo

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
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

    Box(modifier = Modifier.fillMaxWidth().height(500.dp),
        contentAlignment = Alignment.Center
    ) {
        // Surrounding Menu Options
        var degrees = startDegrees
        for (i in 0 until menuOptions.size) {
            var menu = menuOptions[i]
            MenuIcon(
                icon = menu.icon,
                backgroundColor = menu.backgroundColor,
                degrees = degrees,
                distance = distance,
                isMenuOpen = menuOpen.value,
                onClick = {
                    menuOpen.value = !menuOpen.value
                    Log.e("Circle", "Button Out Clicked")
                }
            )
            degrees += degreeDiff
        }

        // Menu Center Icon
        Icon(
            imageVector = if (menuOpen.value) Icons.Default.Close else Icons.Default.Menu,
            contentDescription = "",
            modifier = Modifier.clickable(onClick = {
                menuOpen.value = !menuOpen.value
                Log.e("Circle", "Center Clicked")
            }).background(
                    color = if (menuOpen.value) Color.Gray else Color.White,
                    shape = CircleShape)
                .padding(10.dp),
            tint = Color.Black
        )
    }
}

@Composable
fun MenuIcon(icon: ImageVector, backgroundColor: Color, degrees: Double, distance: Float, isMenuOpen: Boolean, onClick: () -> Unit) {
    // 0
    Box {
        Icon(
            imageVector = icon,
            contentDescription = "",
            modifier = Modifier
                .graphicsLayer(
                    translationX = animateFloatAsState(
                        if (isMenuOpen) distance * Math.cos(Math.toRadians(degrees))
                            .toFloat() else 0f
                    ).value,
                    translationY = animateFloatAsState(
                        if (isMenuOpen) distance * Math.sin(Math.toRadians(degrees))
                            .toFloat() else 0f
                    ).value
                ).clickable(onClick = {
                    onClick.invoke()
                    Log.e("Circle", "Button Clicked")
                })
                .alpha(animateFloatAsState(targetValue = if (isMenuOpen) 1f else 0f).value)
                .background(color = backgroundColor, shape = CircleShape).padding(10.dp),
            tint = Color.White
        )
    }
}

data class MenuOptionModel (
    val icon: ImageVector,
    val backgroundColor: Color
)