package com.saulhervas.listausuariosapp.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.saulhervas.listausuariosapp.data.dao.UserDao
import com.saulhervas.listausuariosapp.data.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: UserDataBase? = null
        fun getDatabase(context: android.content.Context): UserDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDataBase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }


}