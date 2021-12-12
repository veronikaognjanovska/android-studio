package com.example.lab2.models

class Actor(
    override var id: Long,
    override var name: String,
    override var surname: String,
    override var birthYear: Int,
    override var profession: String = "Actor"
) :
    Person(), java.io.Serializable