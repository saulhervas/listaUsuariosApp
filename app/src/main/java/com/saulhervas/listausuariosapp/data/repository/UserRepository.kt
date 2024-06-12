package com.saulhervas.listausuariosapp.data.repository

import androidx.lifecycle.LiveData
import com.saulhervas.listausuariosapp.data.dao.UserDao
import com.saulhervas.listausuariosapp.data.model.User


class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }
}