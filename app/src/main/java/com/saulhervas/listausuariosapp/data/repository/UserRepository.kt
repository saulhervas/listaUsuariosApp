package com.saulhervas.listausuariosapp.data.repository

import com.saulhervas.listausuariosapp.data.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserRepository {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users


    fun addUser(user: User) {
        _users.value = users.value + user
    }

    fun updateUser(index: Int, user: User) {
        _users.value = users.value.toMutableList().apply {
            this[index] = user
        }
    }

    fun deleteUser(index: Int) {
        _users.value = users.value.toMutableList().apply {
            this.removeAt(index)
        }
    }

    fun getUser(index: Int): User? {
        return _users.value.getOrNull(index)
    }
}