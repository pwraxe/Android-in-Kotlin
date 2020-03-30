package com.example.custom_expandable_listview

class SingleGroupObject(
    private var profileImage : String?,
    private var profileName : String?,
    private var viewCount : Int,
    private var likeCount : Int,
    private var commentCount : Int,
    private var downloadCount : Int
) {

    val _profileImage : String?
        get() = profileImage

    val _profileName : String?
        get() = profileName

    val _viewCount : Int
        get() = viewCount

    val _likeCount : Int
        get() = likeCount

    val _commentCount : Int
        get() = commentCount

    val _downloadCount : Int
        get() = downloadCount
}