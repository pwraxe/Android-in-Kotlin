package com.example.jetpackcompose

import android.graphics.Paint.Align
import android.os.Bundle
import android.text.style.AlignmentSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme(darkTheme = true) {

                /**
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    items(20) {
                        LetsStart()
                    }
                }
                **/
                LazyColumn {
                    items(20) {
                        LetsStart()
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LetsStart() {


    Surface(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        color = Color(0xFFF0F0F0),
        shape = RoundedCornerShape(26.dp)) {

        Box {

            Column {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    //Image
                    Image (
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "Sample Image",
                        modifier = Modifier
                            .size(80.dp)
                            .padding(10.dp)
                            .clip(CircleShape))


                    //Column {name, title}
                    Column {
                        Text(
                            text = "Andrew Wilson",
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp

                        )
                        Text(text = "2 Year 3 Month of Experience",
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp
                        )
                    }
                }


                Text(text = "Employee Position",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 10.dp)
                )

                Text(text = "Hello this is long description so user and " +
                    "someone who has less size screen can understand how text " +
                    "works in jetpack compose",
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                )
            }

            Box(modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)) {
                val isStarred = remember { mutableStateOf(false) }

                //start icon
                IconToggleButton(checked = isStarred.value,
                    onCheckedChange = {isStarred.value = !isStarred.value},
                    modifier = Modifier
                        .size(30.dp)
                        .background(Color.White, CircleShape)
                        .padding(5.dp)
                ) {
                    Icon(
                        imageVector = if (isStarred.value) Icons.Default.Star else Icons.Outlined.Star,
                        contentDescription = "Star",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}
