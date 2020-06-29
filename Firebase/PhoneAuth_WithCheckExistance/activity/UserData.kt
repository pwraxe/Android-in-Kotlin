package com.example.phoneauth_withvalidation

class UserData (

    var user_name : String?,
    var user_email : String?,
    var user_mobileNo : Long
){
    constructor() : this("","",0L)
}