package com.arcelortechnology.jetpackcomposestudy

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            LazyColumn{
                items(15){
                    ShowOnDevice()
                }
            }
        }
    }

    @Composable
    fun ShowOnDevice() {

        val context = LocalContext.current

        Card(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(16.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                enabled = true,
                onClickLabel = "OKOKOK", Role.Button
            ) {},
            elevation = 8.dp, shape = RoundedCornerShape(16.dp)) {
            Row(modifier = Modifier
                .padding(16.dp)
                .padding(16.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.android_blue_img),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterVertically)
                )

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Android is software stack mainly design for small scale devices.",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif
                        )
                    Button(modifier = Modifier.align(Alignment.Start), onClick = {
                        Toast.makeText(context, "button clicked", Toast.LENGTH_SHORT).show()
                    }) {
                        Text(text = "Click Here")
                    }
                }


            }
        }
    }


    @Preview(showBackground = true, name = "Loading Preview...")
    @Composable
    fun Overview() {
        Column (modifier = Modifier.verticalScroll(rememberScrollState())){
            repeat(10){
                ShowOnDevice()
            }
        }

    }
}
