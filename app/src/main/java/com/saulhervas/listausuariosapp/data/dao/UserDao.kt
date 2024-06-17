package com.saulhervas.listausuariosapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.saulhervas.listausuariosapp.data.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Update
    suspend fun updateList(list: List<User>)

    @Delete
    suspend fun deleteUser(user: User)

    @Query(value = "SELECT * FROM user_table ORDER BY id ASC")
    fun getAllUsers(): LiveData<List<User>>


}