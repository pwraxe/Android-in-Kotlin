package com.example.mxplayerdemo


class UsersVideoData(
    var videoname : String,
    var videourl : String,
    var videosize : Double,
    var likeCount : Int
)
{
    constructor() : this("","",0.0,0)
}