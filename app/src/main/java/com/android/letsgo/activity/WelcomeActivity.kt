package com.android.letsgo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconToggleButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.letsgo.R
import com.android.letsgo.fragment.AddMeasureFragment
import com.android.letsgo.fragment.AddTravelFragment
import com.android.letsgo.fragment.HomePageFragment
import com.android.letsgo.fragment.MapsFragment
import com.android.letsgo.fragment.ProfileFragment
import androidx.compose.runtime.Composable
import androidx.fragment.app.Fragment
import androidx.navigation.NavHostController

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            WelcomeActivityScreen(navController = navController)
        }
    }

    @Composable
    @Preview
    fun WelcomeActivityScreen(navController: NavHostController = rememberNavController()) {
        val images = listOf(
            R.drawable.home to R.drawable.homepressed,
            R.drawable.travels to R.drawable.travelspressed,
            R.drawable.measure to R.drawable.measurepressed,
            R.drawable.map to R.drawable.mappressed,
            R.drawable.profile to R.drawable.profilepressed
        )

        var selectedIconIndex by remember { mutableStateOf(0) }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(Modifier.fillMaxWidth()) {

                Box(
                    modifier = Modifier
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
                                checked = selectedIconIndex == index,
                                onCheckedChange = { isChecked ->
                                    if (isChecked) {
                                        selectedIconIndex = index
                                        when (index) {
                                            0 -> navController.navigate("home")
                                            1 -> navController.navigate("travels")
                                            2 -> navController.navigate("measure")
                                            3 -> navController.navigate("maps")
                                            4 -> navController.navigate("profile")
                                        }
                                    }
                                }
                            )
                            {
                                Image(
                                    painter = painterResource(if (selectedIconIndex == index) iconPressedRes else iconRes),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(4.dp),
                                    contentScale = ContentScale.Fit
                                )
                            }
                            if (index != images.lastIndex) {
                                Image(
                                    painter = painterResource(R.drawable.line),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(4.dp),
                                    contentScale = ContentScale.Fit
                                )
                            }
                        }
                    }
                }
            }
        }
        NavHost(navController = navController, startDestination = "home") {
            composable("home") { HomePageFragment() }
            composable("travels") { AddTravelFragment() }
            composable("measure") { AddMeasureFragment() }
            composable("maps") { MapsFragment() }
            composable("profile") { ProfileFragment() }
        }
    }
}
