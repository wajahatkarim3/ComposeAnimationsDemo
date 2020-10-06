package com.wajahatkarim3.droidcon.emea2020

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wajahatkarim3.droidcon.emea2020.ui.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VisibilityAnimationFAB() {
    var expanded by remember { mutableStateOf(true) }
    FloatingActionButton(
        onClick = { expanded = !expanded },
        modifier = Modifier
    ) {
        Row(Modifier.padding(start = 16.dp, end = 16.dp)) {
            Icon(
                asset = Icons.Default.Favorite,
                Modifier.align(Alignment.CenterVertically)
            )
            AnimatedVisibility(
                expanded,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(modifier = Modifier.padding(start = 8.dp), text = "Like")
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedBottomNavigation() {
    Card(
        backgroundColor = Color.White,
        elevation = 10.dp,
        modifier = Modifier.padding(20.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(10.dp)) {
            BottomMenuButton(modifier = Modifier.weight(1f), bgColor = homeBgColor, textColor = homeTextColor, text = "Home", icon = Icons.Default.Home)
            BottomMenuButton(modifier = Modifier.weight(1f), bgColor = likeBgColor, textColor = likeTextColor, text = "Like", icon = Icons.Default.FavoriteBorder)
            BottomMenuButton(modifier = Modifier.weight(1f), bgColor = bookmarkBgColor, textColor = bookmarkTextColor, text = "Cart", icon = Icons.Default.ShoppingCart)
            BottomMenuButton(modifier = Modifier.weight(1f), bgColor = settingsBgColor, textColor = settingsTextColor, text = "Settings", icon = Icons.Default.Settings)
        }
    }

}

@Composable
fun BottomMenuButton(modifier: Modifier = Modifier, bgColor: Color, textColor: Color, text: String, icon: VectorAsset) {
    Box (
        modifier = modifier.wrapContentWidth(align = Alignment.CenterHorizontally)
            .background(color = bgColor, shape = RoundedCornerShape(percent = 10))
            .padding(10.dp)
    ) {
        Row {
           Icon(
               asset = icon,
               modifier = Modifier.padding(end = 10.dp).size(20.dp),
               tint = textColor
           )
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