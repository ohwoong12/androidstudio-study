package com.cookandroid.ch14_room_project

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GroupDao {
    @Insert
    suspend fun insert(group: User)

    @Query("DELETE FROM users_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM users_table")
    fun getAllGroups(): LiveData<List<User>>  // suspend 제거 + LiveData로 변경
}
