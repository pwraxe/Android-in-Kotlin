package com.example.cloudfirestoredemo

import com.google.firebase.database.Exclude

data class UserData (
    var name : String,
    var email : String
)
{
    constructor() : this("","")
}