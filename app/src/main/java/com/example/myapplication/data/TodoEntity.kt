package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey val ID: Int,
    val USERID: Int,
    val TITLE: String,
    val COMPLETED: Boolean
)
