package com.codexdroid.roomdatabasestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codexdroid.roomdatabasestudy.room.ItemsViewModel
import com.codexdroid.roomdatabasestudy.room.ViewModelProvider
import com.codexdroid.roomdatabasestudy.screens.AddEditScreen
import com.codexdroid.roomdatabasestudy.screens.HomeScreen
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

enum class Navigation { HOME, EDIT, ADD }

@Composable
fun NextScreen(navController: NavHostController,
               modifier: Modifier = Modifier,
               viewModel: ItemsViewModel = viewModel(factory = ViewModelProvider.factory)) {

    NavHost(navController = navController, startDestination = Navigation.HOME.name) {

        composable(Navigation.HOME.name) {
            HomeScreen(
                itemsViewModel = viewModel,
                onNewItemClick = {
                    viewModel.setId(-1)
                    navController.navigate(Navigation.ADD.name)
                },
                onEditItem = {id ->
                    viewModel.setId(id)
                    navController.navigate(Navigation.ADD.name)
                },
                modifier = modifier)
        }

        composable(Navigation.ADD.name) {
            AddEditScreen(
                viewModel,
                onButtonClick = {
                    navController.popBackStack()
                },
                modifier = modifier)
        }
    }
}
