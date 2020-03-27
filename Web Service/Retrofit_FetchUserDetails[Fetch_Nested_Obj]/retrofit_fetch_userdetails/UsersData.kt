package com.example.retrofit_fetch_userdetails

class UsersData (
    private var id : Int,
    private var name : String?,
    private var username : String?,
    private var email : String?,
    private var address : Address,

    private var phone : String?,
    private var website : String?,
    private var company : Company ) {


    val user_id : Int
        get() = id

    val user_name : String?
        get() = name

    val user_username : String?
        get() = username

    val user_email : String?
        get() = email

    val user_address : Address?
        get() = address

    val user_phone : String?
            get() = phone

    val user_website : String?
        get() = website

    val user_company : Company?
        get() = company


}


class Address(
    private var street : String?,
    private var suite : String?,
    private var city : String?,
    private var zipcode : String?,
    private var geo : GeoLocation){


    val addr_street : String?
        get() = street

    val addr_suite : String?
        get() = suite

    val addr_city : String?
        get() = city

    val addr_zip : String?
        get() = zipcode

    val addr_geo : GeoLocation?
        get() = geo

}

class Company (
    private var name : String?,
    private var catchPhrase : String?,
    private var bs : String?
)
{

    val comp_name : String?
        get() = name

    val comp_catchPharse : String?
        get() = catchPhrase

    val comp_bs : String?
        get() = bs

}
class GeoLocation(
    private var lat : String?,
    private var lng : String?
) {

    val geo_loc_lat : String?
        get() = lat

    val geo_loc_lang : String?
        get() = lng

}