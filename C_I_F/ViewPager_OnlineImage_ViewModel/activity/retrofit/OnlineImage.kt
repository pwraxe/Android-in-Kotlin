package com.example.onlineimageslider.retrofit

data class SingleObject(
    var hits : ArrayList<OnlineImage>
)

data class OnlineImage( var largeImageURL : String )