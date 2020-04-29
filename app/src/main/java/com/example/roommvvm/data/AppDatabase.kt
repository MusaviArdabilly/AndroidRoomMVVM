package com.example.roommvvm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roommvvm.dao.StudentDao
import com.example.roommvvm.entity.Student

//membuat database dengan tabel student dengan menggunakan class Student
@Database(entities = arrayOf(Student::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    //membuat fungsi abstract dari class StudentDao
    abstract fun studentDao(): StudentDao

    companion object {
        //pendeklarasian variabel untu AppDatabase
        private var INSTANCE: AppDatabase? = null

        //fungsi untuk membuat database dengan nama student-database apabila AppDatabase = null
        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "student-database")
                        .build()
                }
            }
            return INSTANCE
        }

        //fungsi untuk menghapus database
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}