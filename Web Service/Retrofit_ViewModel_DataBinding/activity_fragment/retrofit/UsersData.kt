package com.example.retrofit_viewmodel_databinding.retrofit


import kotlinx.android.parcel.RawValue


data class SingleLargeObject(
    var total : Int = 0,
    var totalHits : Int = 0,

    var hits : @RawValue
    ArrayList<UserData>
)


class UserData (
    var largeImageURL : String? = null,
    var views : Int = 0,
    var downloads : Int = 0,
    var favorites : Int = 0,
    var likes : Int = 0,
    var comments : Int = 0,
    var user : String? = null,
    var userImageURL : String? = null
)