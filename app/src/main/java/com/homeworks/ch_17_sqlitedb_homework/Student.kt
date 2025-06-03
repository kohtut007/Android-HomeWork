package com.homeworks.ch_17_sqlitedb_homework

import java.io.Serializable

data class Student(
    val id: Int = 0,
    val name: String,
    val grade: Int,
    val roomNo: String,
    val gender: String,
    val fatherName: String
) : Serializable
