package com.homeworks.ch_17_sqlitedb_homework

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlin.collections.get
import kotlin.toString

class StudentAdapter(
    private val students: List<Student>,
    private val onEdit: (Student) -> Unit,
    private val onDelete: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.tvName)
        val grade = view.findViewById<TextView>(R.id.tvGrade)
        val edit = view.findViewById<ImageView>(R.id.ivEdit)
        val delete = view.findViewById<ImageView>(R.id.ivDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun getItemCount() = students.size

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.name.text = "${student.name} ${if (student.gender == "Male") "♂" else "♀"}"
        holder.grade.text = student.grade.toString()

        holder.edit.setOnClickListener { onEdit(student) }
        holder.delete.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Delete Confirmation")
                .setMessage("Are you sure you want to delete ${student.name}?")
                .setPositiveButton("OK") { _, _ ->
                    onDelete(student)
                    Snackbar.make(holder.itemView, "${student.name} deleted successfully", Snackbar.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }
}
