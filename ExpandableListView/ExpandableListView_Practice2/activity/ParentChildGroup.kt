package com.example.expandablelistview_practice2

class ParentChildGroup (
    private var headerImage : Int,
    private var childList : ArrayList<String> ) {


    val _headerImage : Int
        get() = headerImage

    val _childList : ArrayList<String>
        get() = childList
}