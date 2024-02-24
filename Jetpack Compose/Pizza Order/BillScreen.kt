@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressAndTotalAmount(pizzaViewModel: PizzaViewModel) {

    val orderData by pizzaViewModel.orderData.collectAsState()

    var address by rememberSaveable { mutableStateOf("") }
    var showBill by rememberSaveable { mutableStateOf(false) }

    Column (modifier = Modifier.fillMaxSize()){

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            value = address,
            onValueChange = { address = it },
            label = { Text(
                text = "Home Address",
                style = TextStyle(
                    fontWeight = FontWeight.W500,
                    fontFamily = FontFamily.Serif,
                    fontSize = 14.sp
                )
            ) }
            , textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                fontFamily = FontFamily.Serif),
        )
        if (address.isNotEmpty()) {
            Button(onClick = {
                pizzaViewModel.setAddress(address)
                showBill = true },
                modifier = Modifier
                    .align(alignment = Alignment.End)
                    .padding(end = 10.dp)) {
                Text(text = "Update",
                    style = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                    fontFamily = FontFamily.Serif,
                ))
            }
        }

        if (showBill) {
            Spacer(modifier = Modifier.padding(top = 100.dp))

            Box {
                OutlinedCard (
                    modifier = Modifier.padding(10.dp),
                    border = BorderStroke(1.dp, brush = Brush.linearGradient(listOf(Color.Red, Color.Black)))
                ){
                    Column (modifier = Modifier
                        .background(color = Color.Transparent)
                        .fillMaxWidth()
                        .padding(10.dp)){

                        Text(
                            text = "Address",
                            style = LocalTextStyle.current.copy(
                                fontSize = 20.sp,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.W800
                            )
                        )

                        Text(text = address,
                            style = LocalTextStyle.current.copy(
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Serif)
                            )

                        val gst = orderData?.price?.times((18.0/100.0))

                        Row(modifier = Modifier.padding(top = 50.dp, end = 10.dp)) {
                            Text(
                                text = "Pizza",
                                style = LocalTextStyle.current.copy (
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.W600,
                                    fontFamily = FontFamily.Serif),
                                modifier = Modifier.weight(1f))

                            Text(
                                text = orderData?.pizzaName.toString(),
                                color = Color.Gray,
                                style = LocalTextStyle.current.copy(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily.Serif),
                            )
                        }

                        Row(modifier = Modifier.padding(top = 6.dp, end = 10.dp)) {
                            Text(
                                text = "Size",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W600,
                                modifier = Modifier.weight(1f))

                            Text(
                                text = orderData?.pizzaSize.toString(),
                                color = Color.Gray,
                                style = LocalTextStyle.current.copy(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily.Serif),
                            )
                        }

                        Row(modifier = Modifier.padding(top = 6.dp, end = 10.dp)) {
                            Text(
                                text = "Price",
                                modifier = Modifier.weight(1f),
                                style = LocalTextStyle.current.copy(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.W600,
                                    fontFamily = FontFamily.Serif))

                            Text(
                                text = "₹ ${orderData?.price}",
                                color = Color.Gray,
                                style = LocalTextStyle.current.copy(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily.Serif))
                        }

                        Row(modifier = Modifier.padding(top = 6.dp, end = 10.dp)) {
                            Text(
                                text = "GST Tax",
                                modifier = Modifier.weight(1f),
                                style = LocalTextStyle.current.copy(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.W600,
                                    fontFamily = FontFamily.Serif),
                                )


                            Text(
                                text = "₹ ${gst ?: 0.0}",
                                color = Color.Gray,
                                style = LocalTextStyle.current.copy(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily.Serif),
                            )
                        }

                        Divider(
                            modifier = Modifier.padding(top = 10.dp, end = 10.dp),
                            color = Color.Black,
                            thickness = 1.dp
                        )
                        Row(modifier = Modifier.padding(top = 6.dp, end = 10.dp)) {
                            Text(
                                text = "Total",
                                style = LocalTextStyle.current.copy(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.W600,
                                    fontFamily = FontFamily.Serif),
                                modifier = Modifier.weight(1f))

                            val total = ((gst ?: 0.0) + orderData?.price!!)
                            Text(
                                text = "₹ $total",
                                style = LocalTextStyle.current.copy(
                                    color = Color.Gray,
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Serif)
                            )
                        }
                    }
                }

                Image(painter = painterResource(id = R.drawable.verify),
                    contentDescription = null,
                    modifier = Modifier
                        .size(140.dp)
                        .align(alignment = Alignment.BottomCenter)
                        .padding(bottom = 60.dp))
            }
        }
    }
}


@Preview
@Composable
fun PizzaOrderPreview() {
//    MenuScreen({},viewModel())
//    SizeAndAmountScreen({}, viewModel())
    AddressAndTotalAmount(viewModel())
}
