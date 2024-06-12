package com.saulhervas.listausuariosapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saulhervas.listausuariosapp.data.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query(value = "SELECT * FROM user_table ORDER BY id ASC")
    fun getAllUsers(): LiveData<List<User>>
}