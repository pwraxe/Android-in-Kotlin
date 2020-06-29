package com.example.phoneauth_withvalidation


var userExistence: Int = 0

fun getLength (code : Int) : Int{

    return when(code){
        91 -> 10        // india
        34 -> 8         // spain
        31 -> 9         // netherlands
        27 -> 9         // africa
        32 -> 9         // Belgium
        else -> -1
    }
}

