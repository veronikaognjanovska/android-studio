package com.example.lab4.models

data class Student(
    var id: String,
    var index: String,
    var name: String,
    var surname: String,
    var phoneNumber: String,
    var address: String,
) : java.io.Serializable