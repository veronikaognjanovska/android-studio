package com.example.lab4.viewmodel

import android.app.Application
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lab4.models.Student
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.stream.Collectors

class StudentViewModel(application: Application) : AndroidViewModel(application) {

    var studentListMutableLiveData: MutableLiveData<MutableList<Student>>
    var database = FirebaseDatabase.getInstance()
    var studentsReference = database.getReference("students")
    var databaseHashMap: HashMap<String, HashMap<String, String>> = hashMapOf()

    init {
        this.studentListMutableLiveData = MutableLiveData(mutableListOf<Student>())
        studentsReference.addValueEventListener(object : ValueEventListener {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.value as HashMap<String, HashMap<String, String>>
                databaseHashMap = value
                val list = value.entries.toMutableList().stream().map {
                    Student(
                        it.key,
                        it.value["index"]!!,
                        it.value["name"]!!,
                        it.value["surname"]!!,
                        it.value["phoneNumber"]!!,
                        it.value["address"]!!
                    )
                }.collect(Collectors.toList<Student>())
                studentListMutableLiveData.value = list
                Toast.makeText(getApplication(), "Success", Toast.LENGTH_LONG).show()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(getApplication(), "Error", Toast.LENGTH_LONG).show()
            }

        })
    }

    fun getStudentsListMutableLiveData(): MutableLiveData<MutableList<Student>> {
        return this.studentListMutableLiveData
    }

}