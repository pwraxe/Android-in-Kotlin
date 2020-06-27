package com.example.storeretrive_imagesfromfirebase

class UsersLocalData (

    var userName : String?,
    var userEmail : String?,
    var userMobile : String?,
    var imageUrl : String?,
    var imageName : String?

){
    constructor() : this("","","","","")
}