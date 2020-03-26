package com.example.volley_recycler_load_img

import java.net.URI

class OnlineData(
    private var imageUrl : String?,
    private var userName : String?,
    private var userImageUrl : String?,
    private var views : Int,
    private var downloads : Int,
    private var favorites : Int,
    private var likes : Int ) {


    val _image_url : String?
        get() = imageUrl

    val _user_name : String?
        get() = userName

    val _user_Image_url : String?
        get() = userImageUrl

    val _views : Int
        get() = views

    val _downloads : Int
        get() = downloads

    val _fav : Int
        get() = favorites

    val _likes : Int
        get() = likes

}