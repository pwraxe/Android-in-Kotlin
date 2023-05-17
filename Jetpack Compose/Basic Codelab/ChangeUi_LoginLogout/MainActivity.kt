package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@Preview(showBackground = true)
@Composable
fun PreviewWindow() {
    UpdateUi()
}

@Composable
fun UpdateUi() {
    var shouldShowLogoutDialog by remember { mutableStateOf(true) }
    if(shouldShowLogoutDialog)
        LoginDialog(onClicked = { shouldShowLogoutDialog = false})
    else LogoutDialog(onClicked = { shouldShowLogoutDialog = true})
}

@Composable
fun LoginDialog(onClicked : () -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.inversePrimary,
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(20.dp)
                .background(color = Color.White, RoundedCornerShape(20.dp))
        ) {

            Text(text = "Login?",
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight(600)
            )

            Text(text = "Are you Sure Want to Login?",
                modifier = Modifier.padding(10.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight(100),
            )

            Row {

                Button (
                    onClick = onClicked,
                    modifier = Modifier
                        .padding(10.dp)

                ) {
                    Text(text = "Cancel"
                        //color = Color.Blue
                    )
                }

                Button(onClick = onClicked ,
                    modifier = Modifier.padding(10.dp)) {
                    Text(text = "Login",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight(600),
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }

    }
}

@Composable
fun LogoutDialog(onClicked : () -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.inverseOnSurface,
        modifier = Modifier.fillMaxSize(),
        ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .background(color = Color.White, RoundedCornerShape(20.dp))
        ) {

            Text(text = "Logout",
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight(600)
            )

            val textWithStrike = buildAnnotatedString {
                append("Are you Sure Want to Login? Logout?")
                addStyle(
                    style = SpanStyle(textDecoration = TextDecoration.LineThrough),
                    start = 22,end = 27
                )
            }
            Text(text = textWithStrike,
                modifier = Modifier.padding(10.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight(400),
            )


            Row {

                val customButton = ButtonDefaults.buttonColors(
                    containerColor = Color.Yellow,
                    contentColor = Color.Black
                )
                Button (
                    onClick = onClicked,
                    colors = customButton,
                    modifier = Modifier
                        .padding(10.dp)
                        .border(
                            2.dp, color = Color.Black,
                            shape = RoundedCornerShape(20.dp, 0.dp, 20.dp, 0.dp)
                        )

                ) {
                    Text(text = "Cancel"
                    //color = Color.Blue
                    )
                }

                Button(onClick = onClicked ,
                    modifier = Modifier.padding(10.dp)) {
                    Text(text = "Logout",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight(600),
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }
    }
}
