package com.wajahatkarim3.animations.demo

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wajahatkarim3.animations.demo.ui.*
import java.util.*

@Composable
fun VisibilityAnimationFAB() {
    var expanded by remember { mutableStateOf(true) }
    FloatingActionButton(
        onClick = { expanded = !expanded },
        modifier = Modifier.padding(10.dp)
    ) {
        Row(Modifier.padding(start = 16.dp, end = 16.dp)) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite Icon",
                Modifier.align(Alignment.CenterVertically)
            )
            AnimatedVisibility(
                expanded,
                modifier = Modifier.align(Alignment.CenterVertically),
                enter = slideInHorizontally(
                    initialOffsetX = { 300 },
                    animationSpec = tween(durationMillis = 2000)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { 100 },
                    animationSpec = tween(durationMillis = 2000)
                )
            ) {
                Text(modifier = Modifier.padding(start = 8.dp), text = "Like")
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedBottomNavigation() {

    val bottomOptions = ArrayList<BottomMenuOption>()
    bottomOptions.add(BottomMenuOption(bgColor = homeBgColor, textColor = homeTextColor, text = "Home", icon = Icons.Default.Home))
    bottomOptions.add(BottomMenuOption(bgColor = likeBgColor, textColor = likeTextColor, text = "Like", icon = Icons.Default.FavoriteBorder))
    bottomOptions.add(BottomMenuOption(bgColor = bookmarkBgColor, textColor = bookmarkTextColor, text = "Cart", icon = Icons.Default.ShoppingCart))
    bottomOptions.add(BottomMenuOption(bgColor = settingsBgColor, textColor = settingsTextColor, text = "Settings", icon = Icons.Default.Settings))

    val selectedIndex = remember { mutableStateOf(0) }

    Card(
        backgroundColor = Color.White,
        elevation = 10.dp,
        modifier = Modifier.padding(10.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(10.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            for (i in 0 until bottomOptions.size) {
                BottomMenuButton(
                    selected = i == selectedIndex.value,
                    bgColor = bottomOptions[i].bgColor,
                    textColor = bottomOptions[i].textColor,
                    text = bottomOptions[i].text,
                    icon = bottomOptions[i].icon
                ) {
                    selectedIndex.value = i
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun BottomMenuButton(modifier: Modifier = Modifier, selected: Boolean, bgColor: Color, textColor: Color, text: String, icon: ImageVector, onClick: () -> Unit) {
    Box(
        modifier = modifier.wrapContentWidth(align = Alignment.CenterHorizontally)
            .background(color = if (selected) bgColor else Color.Transparent, shape = RoundedCornerShape(percent = 50))
            .clickable(onClick = onClick)
            .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
    ) {
        Row {
            Icon(
                imageVector = icon,
                contentDescription = "",
                modifier = Modifier.padding(end = 5.dp).size(20.dp),
                tint = if (selected) textColor else Color.Black
            )
            AnimatedVisibility(
                selected,
                modifier = Modifier.align(Alignment.CenterVertically),
            ) {
                Text(
                    text = text,
                    style = TextStyle(
                        color = textColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                )
            }
        }
    }
}

data class BottomMenuOption (
    val bgColor: Color,
    val textColor: Color,
    val text: String,
    val icon: ImageVector
)