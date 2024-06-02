/**
If you want more icons then add following dependency
implementation (libs.androidx.material.icons.extended)
implementation ("androidx.compose.material:compose-material-icons-extended-android")

**/

package com.example.myapplication

import ...

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    val passwordVisualTransformation = remember { PasswordVisualTransformation() }

    TextField(
        value = password,
        onValueChange = { password = it },
        label = {
            Text(text = "Enter Password")
        },

        visualTransformation = if (showPassword)
            VisualTransformation.None
        else passwordVisualTransformation,

        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

        modifier = modifier.fillMaxWidth(),

        trailingIcon = {
            Icon(
                imageVector = if (showPassword)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff,

                contentDescription = null,

                modifier = modifier.clickable {
                    showPassword = !showPassword
                }
            )
        }
    )
}
