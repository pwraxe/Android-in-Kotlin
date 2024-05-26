package com.codexdroid.roomdatabasestudy.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codexdroid.roomdatabasestudy.room.Items
import com.codexdroid.roomdatabasestudy.room.ItemsRepository
import com.codexdroid.roomdatabasestudy.room.ItemsViewModel
import com.codexdroid.roomdatabasestudy.room.ViewModelProvider


class VM: ViewModel()
@Preview
@Composable
fun HomeScreenPreview(modifier: Modifier = Modifier) {
    val viewModel: ItemsViewModel = viewModel(factory = ViewModelProvider.factory)
    HomeScreen(viewModel,{},{})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    itemsViewModel: ItemsViewModel,
    onNewItemClick: () -> Unit,
    onEditItem: (Int) -> Unit,
    modifier: Modifier = Modifier) {

    val list by itemsViewModel.items.collectAsState()

    Scaffold(
         topBar = {
             CenterAlignedTopAppBar(title = {
                 Text(text = "All Items")
             })
         },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onNewItemClick()
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) { paddingValues ->
        Column(modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(color = Color.White)) {

            if (list.isNotEmpty()) {
                LazyColumn {
                    items(list) {
                        ItemsView(items = it, onClickItem = {id ->
                            onEditItem(id)
                        }, onDeleteItem = {
                            itemsViewModel.delete(it)
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun ItemsView(
    items: Items,
    onClickItem: (Int) -> Unit,
    onDeleteItem: () -> Unit,
    modifier: Modifier = Modifier) {

    Row(modifier = modifier
        .fillMaxWidth()
        .padding(top = 10.dp, start = 10.dp, end = 10.dp)
        .clip(CutCornerShape(topStart = 16.dp, bottomEnd = 16.dp))
        .background(color = Color.LightGray)
        .padding(10.dp)
        .clickable {
            onClickItem(items.id)
        }

    ) {

        Box(modifier = modifier
            .size(60.dp)
            .clip(shape = RoundedCornerShape(50.dp))
            .border(2.dp, color = Color.Black, shape = RoundedCornerShape(50.dp))
            .background(Color.Yellow)

        ) {
            val char = if (items.name.isEmpty()) "#" else items.name.first().uppercase()
            Text(text = char,
                fontSize = 25.sp,
                modifier = modifier.align(alignment = Alignment.Center)
            )
        }

        Column (modifier = modifier.align(alignment = Alignment.CenterVertically)){
            Row(modifier = modifier.padding(start = 10.dp)) {
                Text(
                    text = items.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600,
                )

                Spacer(modifier = modifier.weight(1f))

                Text(
                    text = "${items.experience} Years",
                    fontSize = 16.sp,
                    modifier = modifier.align(
                        alignment = Alignment.CenterVertically
                    )
                )
            }

            Row {
                Text(
                    text = items.designation,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W300,
                    modifier = modifier.padding(start = 10.dp)
                )

                Spacer(modifier = modifier.weight(1f))

                IconButton(onClick = {
                    onDeleteItem()
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
            }
        }
    }
}