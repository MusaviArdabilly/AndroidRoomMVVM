package com.example.roommvvm.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roommvvm.entity.Student

@Dao
interface StudentDao {
    //membuat fungsi untuk mengambil semua data dari tabel student
    @Query("Select * from student")
    fun getAll(): List<Student>

    //membuat fungsi untuk insert data ke tabel student
    @Insert
    fun insertStudent(item: Student)
}