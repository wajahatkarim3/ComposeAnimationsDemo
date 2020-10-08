package com.wajahatkarim3.droidcon.emea2020

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.wajahatkarim3.droidcon.emea2020.ui.DroidconEMEA2020Theme

class DroidconActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DroidconEMEA2020Theme {
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
                    bodyContent = {
                        ScrollableColumn {
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
                            //HeartBeatDemo()
                            Spacer(modifier = Modifier.height(40.dp))
                            MovingSquare()
                            Spacer(modifier = Modifier.height(100.dp))

                            HeadingText(text = "Transitions")
                            RayWenderlichFavButton()
                            FlipView(flipDurationMs = 1000,
                                front = {
                                    Image(asset = imageResource(id = R.drawable.card_front),
                                        modifier = Modifier.size(width = 300.dp, height = 150.dp)
                                    )
                                },
                                back = {
                                    Image(asset = imageResource(id = R.drawable.card_back),
                                        modifier = Modifier.size(width = 300.dp, height = 150.dp)
                                    )
                                })
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DroidconEMEA2020Theme {

    }
}