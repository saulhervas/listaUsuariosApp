package com.saulhervas.listausuariosapp.data.repository

import androidx.lifecycle.LiveData
import com.saulhervas.listausuariosapp.data.dao.UserDao
import com.saulhervas.listausuariosapp.data.model.User


class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun updateList(list: List<User>) {
        userDao.updateList(list)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }



}