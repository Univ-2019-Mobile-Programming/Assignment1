package com.example.assignment1

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profile: Profile)

    @Query("SELECT COUNT(*) FROM profile WHERE username LIKE :username")
    fun isExistUsername(username: String): Boolean

    @Query("SELECT COUNT(*) FROM profile WHERE username LIKE :username AND password LIKE :password")
    fun signInCheck(username: String, password: String): Boolean

    @Query("UPDATE profile SET password = :password WHERE username LIKE :username")
    fun updatePassword(username: String, password: String)

    @Query("SELECT * FROM profile WHERE username LIKE :username")
    fun getProfile(username: String): Profile

    @Query("SELECT * FROM profile")
    fun selectAll(): LiveData<List<Profile>>
}