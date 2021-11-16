package com.arcelortechnology.jetpackcomposestudy


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    private val name = "Mr. Developer"
    private val position = "Sr. Android Developer, Team Manager"
    private val androidImage =  "Android Blue Image"

    private var list = ArrayList<String>(25)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repeat(20){
            list.add("Entry of Item No : $it")
        }
        setContent {
            LazyColumn{
                items(list){ message ->
                    MessageCard(messages = message)
                }
            }

        }
    }

    @Composable
    fun MessageCard(messages: String){
        Card(elevation = 10.dp, modifier = Modifier.padding(10.dp)) {
            Row {

                Image(
                    painter = painterResource(id = R.drawable.android_square_img),
                    contentDescription = androidImage,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(40.dp)
                        .clip(shape = CircleShape)
                        .border(2.dp, MaterialTheme.colors.primary, CircleShape)
                )

                Column(modifier = Modifier.padding(all = 10.dp)) {
                    Text(
                        text = messages,
                        color = MaterialTheme.colors.primary
                    )
                    Text(
                        text = position,
                        color = MaterialTheme.colors.secondary,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }


    @Preview
    @Composable
    fun PreviewMessageCard1(){
        Card(elevation = 10.dp) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.android_square_img),
                    contentDescription = androidImage,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(2.dp, MaterialTheme.colors.secondary, CircleShape)
                )

                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = name,
                        color = MaterialTheme.colors.primary
                    )
                    Text(
                        text = position,
                        color = MaterialTheme.colors.secondaryVariant
                    )
                }
            }
        }

    }

}