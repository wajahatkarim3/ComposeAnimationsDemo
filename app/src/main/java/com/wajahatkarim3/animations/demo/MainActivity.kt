package com.wajahatkarim3.animations.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.activity.compose.setContent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FabPosition
import com.wajahatkarim3.animations.demo.ui.ComposeAnimDemoTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAnimDemoTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = stringResource(id = R.string.title_activity_droidcon)) },
                            elevation = 8.dp
                        )
                    },
                    floatingActionButton = {
                        ExplodingFabButton()
                    },
                    floatingActionButtonPosition = FabPosition.End,
                    content = { contentPadding ->
                        LazyColumn(modifier = Modifier.padding(contentPadding)) {
                            item {
                                Spacer(modifier = Modifier.height(20.dp))
                                HeadingText(text = "Circle Menu")
                                CircleMenu()
                                Spacer(modifier = Modifier.height(100.dp))

                                HeadingText(text = "animateContentSize()")
                                AnimateContentSizeDemo()

                                HeadingText(text = "AnimatedVisibility()")
                                VisibilityAnimationFAB()
                                AnimatedBottomNavigation()

                                HeadingText(text = "animate()")
                                ColorAnimation()
                                ScaleAndColorAnimation()
                                Spacer(modifier = Modifier.height(20.dp))
                                GenderSelectAnimation()

                                HeadingText(text = "animatedFloat")
                                HeartBeatDemo()
                                Spacer(modifier = Modifier.height(40.dp))
                                MovingSquare()
                                Spacer(modifier = Modifier.height(100.dp))

                                HeadingText(text = "Transitions")
                                RayWenderlichFavButton()
                                FlipView(flipDurationMs = 1000,
                                    front = {
                                        Image(
                                            painter = painterResource(id = R.drawable.card_front),
                                            contentDescription = "",
                                            modifier = Modifier.size(width = 300.dp, height = 150.dp)
                                        )
                                    },
                                    back = {
                                        Image(
                                            painter = painterResource(id = R.drawable.card_back),
                                            contentDescription = "",
                                            modifier = Modifier.size(width = 300.dp, height = 150.dp)
                                        )
                                    })
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun HeadingText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            color = Color.Black,
            fontSize = 15.sp
        ),
        modifier = Modifier.padding(10.dp)
    )
}