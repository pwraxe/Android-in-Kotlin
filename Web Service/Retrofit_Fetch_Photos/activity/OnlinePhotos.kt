package com.example.retrofit_fetch_photo

class OnlinePhotos(

    private var id : Int,
    private var albumId : Int,
    private var title : String?,
    private var url : String?,
    private var thumbnailUrl : String? ) {


    val _id : Int
        get() = id

    val _album_id : Int
        get() = albumId

    val _title : String?
        get() = title

    val _url_1 : String?
        get() = url

    val _url_2 : String?
        get() = thumbnailUrl


}