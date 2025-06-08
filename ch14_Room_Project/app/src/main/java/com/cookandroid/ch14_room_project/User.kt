package com.cookandroid.ch14_room_project

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")

data class User(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "group_name") val groupName:String?,
    @ColumnInfo(name = "group_member_count") val groupMemberCount:Int
)
