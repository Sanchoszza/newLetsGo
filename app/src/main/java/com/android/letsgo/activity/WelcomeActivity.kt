package com.android.letsgo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.letsgo.R

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }

    @Composable
    fun WelcomeActivityScreen() {
        val icons = listOf(
            R.drawable.home,
            R.drawable.line,
            R.drawable.history,
            R.drawable.line,
            R.drawable.heart,
            R.drawable.line,
            R.drawable.massage,
            R.drawable.line,
            R.drawable.profile
        )

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
                    icons.forEach { icon ->
                        Image(
                            painter = painterResource(id = icon),
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
    @Preview
    @Composable
    fun WelcomeScreen(){
        WelcomeActivityScreen()
    }
}