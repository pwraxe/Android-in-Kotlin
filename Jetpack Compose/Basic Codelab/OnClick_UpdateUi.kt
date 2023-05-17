package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                UpdateUi()
            }
        }
    }
}

@Composable
fun UpdateUi() {
    var shouldShowOnBoarding by remember { mutableStateOf(true) }
    if(shouldShowOnBoarding)
        OnBoardingScreen(onClicked = { shouldShowOnBoarding = false })
    else WelcomeScreen(onClicked = { shouldShowOnBoarding = true })
}

@Composable
fun WelcomeScreen(onClicked: () -> Unit) {
    Surface(color = Color.Gray,
    modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "Hello User, Welcome",
                color = Color.White
            )

            Button(onClick = onClicked) {
                Text(text = "toBoard")
            }
        }
    }
}

@Composable
fun OnBoardingScreen(onClicked : () -> Unit) {

    Surface(
        color = MaterialTheme.colorScheme.inversePrimary,
        modifier = Modifier.fillMaxSize()
    ) {
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                ){
                Text(text = "Hello There, Welcome to onboard")
                Button(onClick = onClicked, modifier = Modifier.padding(top = 10.dp)) {
                    Text(text = "toWelcome")
                }
            }
    }
}

@Composable
@Preview(showBackground = true)
fun Design()  {
    UpdateUi()
}
