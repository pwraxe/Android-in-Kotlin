
enum class Screens { MENU, SIZE, BILL }

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme(darkTheme = false) {
                UpdateScreen()
            }
        }
    }
}

@Composable
fun UpdateScreen(navController: NavHostController = rememberNavController(), pizzaViewModel: PizzaViewModel = viewModel()) {

    NavHost(
        navController = navController,
        startDestination = Screens.MENU.name) {

        composable(Screens.MENU.name) {
            MenuScreen(onNextClicked = {
                navController.navigate(Screens.SIZE.name)
            }, pizzaViewModel)
        }

        composable(Screens.SIZE.name) {
            SizeAndAmountScreen(onNextClicked = {
                    navController.navigate(Screens.BILL.name)
            }, pizzaViewModel)
        }
        composable(Screens.BILL.name) {
            AddressAndTotalAmount(pizzaViewModel)
        }
    }
}

@Preview
@Composable
fun PizzaOrderPreview() {
   MenuScreen({},viewModel())
   // SizeAndAmountScreen({}, viewModel())
   // AddressAndTotalAmount()
}
