package com.example.materialdesignpart2

class OnlineData  (
    private var userImageUrl : String?,
    private var username : String?,
    private var views : Int?,
    private var downloads : Int?,
    private var likes : Int?,
    private var comments : Int?) {


    val _userDP : String?
        get() = userImageUrl

    val _username : String?
        get() = username

    val _views : Int?
        get() = views

    val _downloads : Int?
        get() = downloads

    val _likes : Int?
        get() = likes

    val  _comment : Int?
        get() = comments


}