package com.homeworks.ch_17_sqlitedb_homework

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.homeworks.ch_17_sqlitedb_homework.databinding.ActivityAddStudentBinding

class AddStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddStudentBinding
    private lateinit var db: StudentDatabaseHelper
    private var studentId: Int? = null  // For editing

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = StudentDatabaseHelper(this)

        // Check if this is an edit operation
        val student = intent.getSerializableExtra("student") as? Student
        if (student != null) {
            studentId = student.id
            binding.etName.setText(student.name)
            binding.etGrade.setText(student.grade.toString())
            binding.etRoomNo.setText(student.roomNo)
            binding.etFatherName.setText(student.fatherName)
            if (student.gender == "Male") {
                binding.rbMale.isChecked = true
            } else {
                binding.rbFemale.isChecked = true
            }
            binding.btAddStudent.text = "Update Student"
        }

        binding.btAddStudent.setOnClickListener {
            val name = binding.etName.text.toString()
            val gradeText = binding.etGrade.text.toString()
            val roomNoText = binding.etRoomNo.text.toString()
            val gender = if (binding.rbMale.isChecked) "Male" else "Female"
            val fatherName = binding.etFatherName.text.toString()

            if (name.isNotEmpty() && gradeText.isNotEmpty() && roomNoText.isNotEmpty()) {
                val updatedStudent = Student(
                    id = studentId ?: 0,
                    name = name,
                    grade = gradeText.toInt(),
                    roomNo = roomNoText,
                    gender = gender,
                    fatherName = fatherName
                )

                if (studentId != null) {
                    db.updateStudent(updatedStudent)
                } else {
                    db.insertStudent(updatedStudent)
                }

                finish()
            } else {
                Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
