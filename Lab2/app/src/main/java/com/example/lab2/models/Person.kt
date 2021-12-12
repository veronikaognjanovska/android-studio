package com.example.lab2.models

abstract class Person : java.io.Serializable {
    abstract var id: Long
    abstract var name: String
    abstract var surname: String
    abstract var birthYear: Int
    abstract var profession: String
}