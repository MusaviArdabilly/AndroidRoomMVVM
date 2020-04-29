package com.example.roommvvm.viewmodel

import android.app.Application
import android.graphics.Movie
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roommvvm.data.AppDatabase
import com.example.roommvvm.entity.Student
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class NewStudentViewModel(application: Application) : AndroidViewModel(application) {
    //pendeklarasian Appdatabase
    private val mDb: AppDatabase? = AppDatabase.getInstance(application)
    private val allStudent = MutableLiveData<List<Student>>()

    //fungsi untuk store(insert) data
    fun storeMovie(title: String) {
        //pendeklarasian untuk class Student dan penerimaan data dari parameter class store
        val student = Student()
        student.name = title

        GlobalScope.launch {
            //code untuk melakukan insert data dengan memanggil fungsi insertStudent pada class StudentDao
            mDb?.studentDao()?.insertStudent(student)
        }
    }

    fun retrieveStudent(): LiveData<List<Student>> {

        GlobalScope.launch {
            //mendeklarasikan variable untuk mendapatkan data dari database
            val list = mDb?.studentDao()?.getAll()

            //penghitungan jumlah data yang ada pada table student
            Timber.i("Data yang ada sejumlah {${list?.size}}")
            allStudent.postValue(list)
        }

        //mereturn value dari allStudent
        return allStudent
    }
}