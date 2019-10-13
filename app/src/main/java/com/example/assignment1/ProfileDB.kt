package com.example.assignment1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Profile::class], version = 1)
abstract class ProfileDB: RoomDatabase() {
    abstract fun profileDao(): ProfileDao

    companion object {
        private var INSTANCE: ProfileDB? = null

        fun getInstance(context: Context): ProfileDB? {
            if(INSTANCE == null){
                synchronized(ProfileDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                                                    ProfileDB::class.java,
                                                    "profile.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destoryInstance(){
            INSTANCE = null
        }
    }
}