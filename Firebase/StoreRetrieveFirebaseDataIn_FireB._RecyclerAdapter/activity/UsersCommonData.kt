package com.example.store_retrieved_data_firebase

data class UsersCommonData (

    var name : String?,
    var email : String?,
    var mobile : Long?,
    var usersAddData : UsersAddressData ){

    constructor() : this("","",0L,UsersAddressData("","","","","",""))
}


data class UsersAddressData(

    var street : String?,
    var area : String?,
    var town :String?,
    var city : String?,
    var state : String?,
    var country : String ){

    constructor() : this("","","","","","")
}