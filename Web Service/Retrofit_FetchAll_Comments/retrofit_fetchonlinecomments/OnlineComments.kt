package com.example.retrofit_fetchonlinecomments

class OnlineComments {

    private var postId : Int = 0
    private var id : Int = 0
    private var name : String? = null
    private var email : String? = null
    private var body : String? = null

    fun getPostID() : Int = postId
    fun getID() : Int = id
    fun getName() : String? = name
    fun getEmail() : String? = email
    fun getBody() : String? = body

}
