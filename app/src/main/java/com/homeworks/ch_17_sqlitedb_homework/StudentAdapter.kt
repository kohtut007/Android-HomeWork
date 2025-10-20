package com.homeworks.ch_17_sqlitedb_homework

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.homeworks.ch_17_sqlitedb_homework.databinding.ListItemStudentBinding

class StudentAdapter(
    private val students: List<Student>,
    private val onEdit: (Student) -> Unit,
    private val onDelete: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(val binding: ListItemStudentBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ListItemStudentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudentViewHolder(binding)
    }

    override fun getItemCount() = students.size

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        val context = holder.itemView.context
        val binding = holder.binding

        binding.tvName.text = "${student.name}"
        binding.tvGrade.text = student.grade.toString()

        binding.ivGender.setImageResource(if (student.gender == "Male") R.drawable.outline_male_24 else R.drawable.outline_female_24)

        binding.ivEdit.setOnClickListener { onEdit(student) }

        binding.ivDelete.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete Confirmation")
                .setMessage("Are you sure you want to delete ${student.name}?")
                .setPositiveButton("OK") { _, _ ->
                    onDelete(student)
                    Snackbar.make(
                        holder.itemView,
                        "${student.name} deleted successfully",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }
}

