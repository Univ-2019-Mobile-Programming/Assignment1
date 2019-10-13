package com.example.assignment1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
class Profile(@PrimaryKey(autoGenerate = true) var id: Long?,
              @ColumnInfo(name = "username") var username: String,
              @ColumnInfo(name = "password") var password: String,
              @ColumnInfo(name = "name") var name: String,
              @ColumnInfo(name = "phoneNumber") var phoneNumber: String,
              @ColumnInfo(name = "address") var address: String){
    constructor(): this(null, "", "", "", "", "")
}