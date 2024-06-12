package com.saulhervas.listausuariosapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val favoriteColor: String,
    val birthDate: String,
    val favoriteCity: String,
    val favoriteNumber: Int,
    val location: String

)