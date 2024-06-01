package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Show()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ShowPreview(modifier: Modifier = Modifier) {
    Show(modifier)
}

@Composable
fun Show(modifier: Modifier = Modifier) {

    val list = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(state = list) {
            items(80) {
                ItemsView(str = "Hello from No ${it + 1}")
            }
        }

        FloatingActionButton(onClick = {
            coroutineScope.launch {
                list.animateScrollToItem(0)
            }
        },
            modifier = modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(10.dp)) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = null)
        }
    }
}

@Preview
@Composable
fun ItemsPreview(modifier: Modifier = Modifier) {
    ItemsView("Akshay",modifier)
}
@Composable
fun ItemsView(str: String, modifier: Modifier = Modifier) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(horizontal = 10.dp).fillMaxWidth()
            .padding(vertical = 2.dp,)
            .background(color = Color.White)){

        Box(modifier = modifier
            .padding(6.dp)
            .background(color = Color.Blue, shape = RoundedCornerShape(100.dp))
            .padding(start = 10.dp, end = 10.dp, top = 6.dp, bottom = 6.dp)) {
            Text(text = str.first().uppercase(), color = Color.Yellow)
        }
        Text(text = str, modifier = modifier.padding(end = 10.dp))
    }
}
