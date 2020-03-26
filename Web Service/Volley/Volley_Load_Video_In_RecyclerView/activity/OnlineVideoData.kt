package com.example.volley_recycler_load_video

class OnlineVideoData(

    private var userImageUrl : String?,
    private var username : String?,
    private var videoURL : String?,
    private var views : Int,
    private var downloads : Int,
    private var like : Int,
    private var comment : Int ) {


    val _userImageURL : String?
        get() = userImageUrl

    val _username : String?
        get() = username

    val _videoURL : String?
        get() = videoURL

    val _views : Int
        get() = views

    val _downloads : Int
        get() = downloads

    val _like : Int
        get() = like

    val _comment : Int
        get() = comment

}