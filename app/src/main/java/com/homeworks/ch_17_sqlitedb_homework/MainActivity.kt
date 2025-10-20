package com.homeworks.ch_17_sqlitedb_homework

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.homeworks.ch_17_sqlitedb_homework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: StudentDatabaseHelper
    private lateinit var adapter: StudentAdapter
    private lateinit var studentList: MutableList<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = StudentDatabaseHelper(this)
        studentList = db.getAllStudents().toMutableList()


        adapter = StudentAdapter(studentList,
            onEdit = { student ->
                val intent = Intent(this, AddStudentActivity::class.java)
                intent.putExtra("student", student)
                startActivity(intent)
            },
            onDelete = { student ->
                db.deleteStudent(student.id)
                studentList.remove(student)
                adapter.notifyDataSetChanged()
            })

        binding.rvStudents.adapter = adapter

        binding.fbAdd.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddStudentActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        studentList.clear()
        studentList.addAll(db.getAllStudents())
        adapter.notifyDataSetChanged()
    }
}
