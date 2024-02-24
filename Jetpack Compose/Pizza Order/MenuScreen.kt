@Composable
fun MenuScreen(onNextClicked: () -> Unit, pizzaViewModel: PizzaViewModel, modifier: Modifier = Modifier) {

    val selectedItem by pizzaViewModel.orderData.collectAsState()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween ) {

        Text(
            text = "Welcome to Pizza Shop",

            //When you want your own font and size to app,
            // override your font and size by default in android
            style = LocalTextStyle.current.copy(
                fontSize = 25.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center
            ),

            modifier = modifier
                .padding(40.dp)
                .align(alignment = Alignment.CenterHorizontally))


        MenuList(menu = menu, onCardClick = {
            pizzaViewModel.setSelectedPizzaData(it)
        },selectedItem)

        Button(onClick = onNextClicked,
            modifier = modifier
                .align(Alignment.End)
                .padding(5.dp), enabled = selectedItem?.id != 0) {
            Text(text = "Next")
        }
    }
}

@Composable
fun MenuList(menu: List<OrderData>, onCardClick: (OrderData?) -> Unit, selectedItem: OrderData?) {

    Column {
        menu.forEach {
            MenuItem(it,
                onCardClick = { data ->
                    onCardClick(data)
                }, selectedItem)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuItem(menuData: OrderData, onCardClick: (OrderData?) -> Unit, selectedItem: OrderData?) {

    val selectedItemColor = if (selectedItem != null && selectedItem.id == menuData.id) Color(0xFF6750A3) else Color.White
    val selectedTextColor = if (selectedItem != null && selectedItem.id == menuData.id) Color.White else Color.Black

    OutlinedCard (modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp, vertical = 5.dp), onClick = {
        onCardClick(menuData)
    }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = selectedItemColor)) {
            Image(
                painter = painterResource(id = menuData.image),
                modifier = Modifier
                    .size(100.dp)
                    .padding(10.dp)
                    .clip(shape = CircleShape),
                contentScale = ContentScale.Crop,
                contentDescription = null)

            Text(
                text = menuData.pizzaName,
                style = LocalTextStyle.current.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = FontFamily.Serif),
                color = selectedTextColor,
                modifier = Modifier.align(Alignment.CenterVertically))
        }
    }
}


@Preview
@Composable
fun PizzaOrderPreview1() {
    // MenuScreen({},viewModel())
   SizeAndAmountScreen({}, viewModel())
   // AddressAndTotalAmount(viewModel())
}

