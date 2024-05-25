package com.codexdroid.splashstudy

import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codexdroid.roomdatabasestudy.ui.theme.RoomDatabaseStudyTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            RoomDatabaseStudyTheme {
                var isSplashVisible by remember { mutableStateOf(true) }
                
                LaunchedEffect(Unit) {
                    delay(3000)
                    isSplashVisible = false
                }

                if(isSplashVisible) {
                    SplashScreen()
                }
                else {
                    val navController = rememberNavController()
                    NextScreen(navController = navController)
                }
            }
        }
    }
}


@Composable
fun SplashScreen(modifier: Modifier = Modifier) {

    Box(modifier = modifier
        .fillMaxSize()
        .background(color = Color.White)) {

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = modifier.align(alignment = Alignment.Center)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = modifier.align(alignment = Alignment.Center)
        )
    }
}

@Composable
fun NextScreen(navController: NavHostController,
               modifier: Modifier = Modifier) {

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }
    }
}
@Preview
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .fillMaxSize()
        .background(color = Color.White)) {

        Text(
            text = "Welcome to Home Screen",
            modifier = modifier.align(alignment = Alignment.Center)
        )
    }
}
