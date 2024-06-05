package com.saulhervas.listausuariosapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saulhervas.listausuariosapp.data.model.User
import com.saulhervas.listausuariosapp.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val repository = UserRepository()
    val users: StateFlow<List<User>> = repository.users

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUse: StateFlow<User?> = _currentUser


    fun addUser(user: User) {
        viewModelScope.launch {
            repository.addUser(user)
        }
    }

    fun updateUser(index: Int, user: User) {
        viewModelScope.launch {
            repository.updateUser(index, user)
        }
    }

    fun deleteUser(index: Int) {
        viewModelScope.launch {
            repository.deleteUser(index)
        }
    }

    fun getUser(index: Int) {
        _currentUser.value = repository.getUser(index)
    }

    fun setCurrentUser(user: Int) {
        _currentUser.value = repository.getUser(user)
    }
}

