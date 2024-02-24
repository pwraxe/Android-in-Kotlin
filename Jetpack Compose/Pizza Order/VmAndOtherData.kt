package com.codexdroid.compose.ui.theme

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.codexdroid.compose.R
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

fun toJson(data:Any?): String = Gson().toJson(data)
val menu = listOf(
    OrderData(1,"Cheez Red Pizza", R.drawable.pizza_small),
    OrderData(2,"Cheez Pan Pizza", R.drawable.pizza_medium),
    OrderData(3,"Tomato Cheez Pizza", R.drawable.pizza_large)
)

val sizeAndAmount = listOf(
    OrderData(pizzaSize = "Small", price = 99.0),
    OrderData(pizzaSize = "Medium", price = 199.0),
    OrderData(pizzaSize = "Large", price = 399.0)
)


data class OrderData(
    val id: Int = 0,
    var pizzaName: String = "",
    @DrawableRes val image: Int = 0,
    var pizzaSize: String = "",
    var price: Double = 0.0,
    var address: String = ""
)

class PizzaViewModel : ViewModel() {

    private val _orderData = MutableStateFlow<OrderData?>(OrderData())
    val orderData: StateFlow<OrderData?> = _orderData.asStateFlow()

    fun setSelectedPizzaData(orderData: OrderData?) {
        _orderData.update {
            it?.copy(
                id = orderData?.id ?: 0,
                pizzaName = orderData?.pizzaName.toString(),
                image = orderData?.image ?: 0
            )
        }
    }

    fun setSizeAndAmountData(orderData: OrderData?) {
        _orderData.update {
            it?.copy(
                pizzaSize = orderData?.pizzaSize.toString(),
                price = orderData?.price ?: 0.0)
        }
    }

    fun setAddress(address: String) {
        _orderData.update {
            it?.copy(address = address)
        }
    }
}
