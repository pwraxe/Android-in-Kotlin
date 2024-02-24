@Composable
fun SizeAndAmountScreen(onNextClicked: () -> Unit,pizzaViewModel: PizzaViewModel) {

    val orderData by pizzaViewModel.orderData.collectAsState()

    val image: Int
    val size: Dp
    when(orderData?.image) {
        R.drawable.pizza_small -> {
            image = R.drawable.pizza_small
            size = 80.dp
        }
        R.drawable.pizza_medium -> {
            image = R.drawable.pizza_medium
            size = 100.dp
        }
        R.drawable.pizza_large -> {
            image = R.drawable.pizza_large
            size = 120.dp
        }
        else-> {
            image = R.drawable.android_logo
            size = 100.dp
        }
    }
    Column {

        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .padding(20.dp)
                .size(size)
                .clip(shape = CircleShape)
                .align(alignment = Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop )

        SizeAmountList(list = sizeAndAmount, onCardClick = {
            pizzaViewModel.setSizeAndAmountData(it)
        }, orderData!!)

        Button(onClick = onNextClicked,
            enabled = orderData?.pizzaSize?.isNotEmpty() == true,
            modifier = Modifier
                .align(Alignment.End)
                .padding(10.dp)) {
            Text(text = "Next")
        }
    }
}

@Composable
fun SizeAmountList(
    list: List<OrderData>,
    onCardClick: (OrderData?) -> Unit,
    selectedSize: OrderData
) {

    list.forEach {
        SizeAndAmount(it, onSizeAmountClicked = { data ->
            onCardClick(data)
        }, selectedSize)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SizeAndAmount(
    orderData: OrderData,
    onSizeAmountClicked: (OrderData) -> Unit, selectedSize: OrderData) {

    val selectedCardColor = if (orderData.pizzaSize == selectedSize.pizzaSize) Color(0xFF6750A3) else Color.White
    val selectedCardTextColor = if (orderData.pizzaSize == selectedSize.pizzaSize) Color.White else Color.Black
    Row (modifier = Modifier.fillMaxWidth()) {

        OutlinedCard (modifier = Modifier
            .weight(1f)
            .padding(6.dp),
            onClick = { onSizeAmountClicked(orderData) }) {

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = selectedCardColor)) {

                Text(
                    text = orderData.pizzaSize,
                    style = LocalTextStyle.current.copy(
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.W200
                    ),
                    color = selectedCardTextColor,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }

        OutlinedCard(modifier = Modifier
            .weight(0.5f)
            .padding(vertical = 6.dp, horizontal = 6.dp)) {

            Row (horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = selectedCardColor)
            ){

                Text(text = "₹ ${orderData.price}",
                    style = LocalTextStyle.current.copy(
                        fontSize = 20.sp,
                        textAlign = TextAlign.End,
                        fontFamily = FontFamily.Serif,
                    ),
                    color = selectedCardTextColor,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun PizzaOrderPreview2() {
//    MenuScreen({},viewModel())
    SizeAndAmountScreen({}, viewModel())
//    AddressAndTotalAmount()
}
