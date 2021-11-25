package com.arcelortechnology.jetpackcomposestudy


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ProClass(var proName: String, var type: String)

class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            //private var langList = emptyList<ProClass>()
            val langList = mutableListOf<ProClass>()
    
            repeat(10){
                langList.add(it,ProClass("Android $it","Language $it"))            
            }
            
            LazyColumn(modifier = Modifier.padding(vertical = 12.dp)){
                items(items = langList){ proData ->
                    ShowOnDevice(proData.proName,proData.type)
                }
            }
        }
    }

    @Composable
    fun ShowOnDevice(name: String, type: String){

        var isButtonClicked by remember { mutableStateOf(false)}
        val expandScreen by animateDpAsState(
            targetValue = if(isButtonClicked) 64.dp else 0.dp,
            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow))

        Surface(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)) {

            Card(backgroundColor = MaterialTheme.colors.secondary,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = 8.dp
            ) {
                Row(modifier = Modifier.padding(bottom = expandScreen.coerceAtLeast(0.dp))){
                   Image(painter = painterResource(id = R.drawable.android_square_img),
                       alignment = Alignment.Center,
                       contentDescription = "Some Desc",
                       modifier = Modifier
                           .padding(20.dp)
                           .clip(CircleShape)
                           .border(2.dp, Color.Black, CircleShape)
                           .size(68.dp))

                    Column (modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()){

                        Text(text = name, fontSize = 24.sp)
                        Text(text = type)
                        Button(onClick = { isButtonClicked= !isButtonClicked }) {
                            Text(text = if(!isButtonClicked) "Show Less" else "Show More")
                        }

                        if(isButtonClicked){
                            Text(text = ("I am hidden text, ").repeat(5))
                        }
                    }
                }
            }
        }
    }

    @Preview(showBackground = true, name = "Hello Akshay")
    @Composable
    fun Overview(){

        //private var langList = emptyList<ProClass>()
        val langList = mutableListOf<ProClass>()

        repeat(10){
            langList.add(it,ProClass("Android $it","Language $it"))
        }

        LazyColumn(modifier = Modifier.padding(vertical = 12.dp)){
            items(items = langList){ proData ->
                ShowOnDevice(proData.proName,proData.type)
            }
        }
    }
}
