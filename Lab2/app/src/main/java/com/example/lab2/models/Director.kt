package com.example.lab2.models

class Director(
    override var id: Long,
    override var name: String,
    override var surname: String,
    override var birthYear: Int,
    override var profession: String = "Director"
) :
    Person(), java.io.Serializable
