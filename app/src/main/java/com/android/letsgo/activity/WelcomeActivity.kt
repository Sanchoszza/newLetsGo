package com.android.letsgo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomAppBar
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.letsgo.R
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }

    @Composable
    fun WelcomeActivityScreen() {
        val images = listOf(
            R.drawable.home to R.drawable.homepressed,
            R.drawable.travels to R.drawable.travelspressed,
            R.drawable.measure to R.drawable.measurepressed,
            R.drawable.map to R.drawable.mappressed,
            R.drawable.profile to R.drawable.profilepressed
        )

        val selectedIconIndex = remember { mutableStateOf(-1) }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(color = Color(0xFFF6F3F3))
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    images.forEachIndexed { index, (iconRes, iconPressedRes) ->
                        IconToggleButton(
                            checked = selectedIconIndex.value == index,
                            onCheckedChange = { selectedIconIndex.value = index }
                        ) {
                            Image(
                                painter = painterResource(if (selectedIconIndex.value == index) iconPressedRes else iconRes),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(4.dp)
                            )
                        }
                        if (index != images.lastIndex) {
                            Image(
                                painter = painterResource(R.drawable.line),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(4.dp)
                            )
                        }
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun PreviewWelcomeScreen() {
        WelcomeActivityScreen()
    }

}
