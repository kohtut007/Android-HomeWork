package com.homeworks.ch_17_sqlitedb_homework

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class StudentDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "students.db"
        private const val DATABASE_VERSION = 2
        private const val TABLE_NAME = "students"

        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_GRADE = "grade"
        private const val COLUMN_ROOM_NO = "roomNo"
        private const val COLUMN_GENDER = "gender"
        private const val COLUMN_FATHER_NAME = "fatherName"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT NOT NULL,
                $COLUMN_GRADE TEXT NOT NULL,
                $COLUMN_ROOM_NO TEXT NOT NULL,
                $COLUMN_GENDER TEXT NOT NULL,
                $COLUMN_FATHER_NAME TEXT NOT NULL
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }
    }

    // ✅ INSERT
    fun insertStudent(student: Student): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, student.name)
            put(COLUMN_GRADE, student.grade)
            put(COLUMN_ROOM_NO, student.roomNo)
            put(COLUMN_GENDER, student.gender)
            put(COLUMN_FATHER_NAME, student.fatherName)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    // ✅ READ ALL
    fun getAllStudents(): List<Student> {
        val students = mutableListOf<Student>()
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor.moveToFirst()) {
            do {
                val student = Student(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    grade = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_GRADE)),
                    roomNo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ROOM_NO)),
                    gender = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER)),
                    fatherName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FATHER_NAME))
                )
                students.add(student)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return students
    }

    // ✅ READ SINGLE (optional)
    fun getStudentById(id: Int): Student? {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_NAME, null, "$COLUMN_ID = ?", arrayOf(id.toString()),
            null, null, null
        )
        var student: Student? = null
        if (cursor.moveToFirst()) {
            student = Student(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                grade = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_GRADE)),
                roomNo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ROOM_NO)),
                gender = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER)),
                fatherName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FATHER_NAME))
            )
        }
        cursor.close()
        return student
    }

    // ✅ UPDATE
    fun updateStudent(student: Student): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, student.name)
            put(COLUMN_GRADE, student.grade)
            put(COLUMN_ROOM_NO, student.roomNo)
            put(COLUMN_GENDER, student.gender)
            put(COLUMN_FATHER_NAME, student.fatherName)
        }
        return db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(student.id.toString()))
    }

    // ✅ DELETE
    fun deleteStudent(id: Int): Int {
        val db = writableDatabase
        return db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }
}
