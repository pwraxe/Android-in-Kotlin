package com.example.timepass

class OnlineData (private var imageURL : String?,
                  private var views : Int?,
                  private var downloads : Int?,
                  private var favorites : Int?,
                  private var likes : Int?,
                  private var comments : Int?,
                  private var username : String?,
                  private var userDP : String?) {

    val _image_url : String?
            get() = imageURL

    val _views : Int?
        get() = views

    val _downloads : Int?
        get() = downloads

    val _favorites : Int?
        get() = favorites

    val _likes : Int?
        get() = likes

    val _comments : Int?
        get() = comments

    val _username : String?
        get() = username

    val _userDP : String?
        get() = userDP


}