package com.codexdroid.roomdatabasestudy.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codexdroid.roomdatabasestudy.room.Items
import com.codexdroid.roomdatabasestudy.room.ItemsViewModel
import com.codexdroid.roomdatabasestudy.room.ViewModelProvider

@Preview
@Composable
fun AddScreenPreview(modifier: Modifier = Modifier) {
    val viewModel: ItemsViewModel = viewModel(factory = ViewModelProvider.factory)
    AddEditScreen(viewModel, {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(
    itemsViewModel: ItemsViewModel,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier) {

    //val itemsViewModel: ItemsViewModel = viewModel(factory = ViewModelProvider.factory)
    val uiState by itemsViewModel.uiState.collectAsState()
    val item by itemsViewModel.item.collectAsState()

    var name by remember { mutableStateOf(item.name.ifEmpty { "" }) }
    var experiance by remember { mutableStateOf(item.experience.toString().ifEmpty { "" }) }
    var designation by remember { mutableStateOf(item.designation.ifEmpty { "" }) }

    Scaffold(
        topBar = {
            val label = if (uiState.id == -1) "Add Item" else "Update Item"
            CenterAlignedTopAppBar(title = { Text(text = label) } )
        }
    ) { padding ->
        Column (modifier = modifier
            .padding(padding)
            .background(color = Color.White)
            .fillMaxSize()) {

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),

                label = {
                    Text(text = "Enter Name")
                },
                keyboardOptions = KeyboardOptions().copy(
                    keyboardType = KeyboardType.Text
                )
            )

            OutlinedTextField (
                value = experiance.toString(),
                onValueChange = { experiance = it },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp),
                label = {
                    Text(text = "Enter Experience")
                },
                keyboardOptions = KeyboardOptions().copy(
                    keyboardType = KeyboardType.Number
                )
            )

            OutlinedTextField(
                value = designation,
                onValueChange = { designation = it },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp),

                label = {
                    Text(text = "Enter Designation")
                },
                keyboardOptions = KeyboardOptions().copy(
                    keyboardType = KeyboardType.Text
                )
            )

            OutlinedButton(onClick = {
                if (uiState.id == -1) {
                    itemsViewModel.save(Items(0,name,experiance.toIntOrNull() ?: 0,designation))
                } else {
                    itemsViewModel.update(Items(id = uiState.id, name,experiance.toIntOrNull() ?: 0, designation))
                }
                name = ""
                experiance = ""
                designation = ""
                onButtonClick()
            }, modifier = modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally)
                .padding(30.dp),
                enabled = name.isNotEmpty() && experiance.isNotEmpty() && designation.isNotEmpty()
            ) {
                val label = if (uiState.id == -1) "Submit" else "Update"
                Text(text = label)
            }
        }
    }
}
