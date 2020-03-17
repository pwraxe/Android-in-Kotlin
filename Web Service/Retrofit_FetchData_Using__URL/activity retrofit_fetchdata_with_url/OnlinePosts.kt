package com.example.retrofit_fetchdata_with_url

import retrofit2.http.Body

class OnlinePosts {

    private var userId : Int = 0
    private var id : Int = 0
    private var title : String? = null
    private var body : String? = null

    fun getUserID() : Int = userId
    fun getId() : Int = id
    fun getTitle() : String? = title
    fun getBody() : String? = body


}