package com.example.roomdb_practice.room_stuffs

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class UsersData (

    @PrimaryKey
    var u_id : Int,

    @ColumnInfo(name = "COL_NAME")
    var name : String,

    @ColumnInfo(name = "COL_EMAIL")
    var email : String,

    @ColumnInfo(name = "COL_MOBILE")
    var mobile :Long

)