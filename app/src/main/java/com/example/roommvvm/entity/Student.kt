package com.example.roommvvm.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
//deklarasi data class Student
data class Student (
    //deklarasi id sebagai primary key dan autoincrement
    @PrimaryKey(autoGenerate = true) var id: Int? = null,

    //deklarasi tipe data pada field name
    @ColumnInfo var name: String = ""
)