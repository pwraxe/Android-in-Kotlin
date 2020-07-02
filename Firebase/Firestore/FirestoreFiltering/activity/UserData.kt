package com.example.cloudfirestoredemo

import com.google.firebase.database.Exclude

data class UserData (
    val views : Int,
    var name : String,
    var email : String
)
{
    constructor() : this(0,"","")
}