package com.example.roomdatabase.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.EditCode
import com.example.roomdatabase.EditTask
import com.example.roomdatabase.GradeViewModel
import com.example.roomdatabase.R
import com.example.roomdatabase.Task
import com.example.roomdatabase.id

class TaskListAdapter(val gradeViewModel: GradeViewModel, val applicationContext: Context) : ListAdapter<Task, TaskListAdapter.TaskViewHolder>(
    TasksComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, gradeViewModel, applicationContext)
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val task_name: TextView = itemView.findViewById(R.id.task_name)
        private val task_time: TextView = itemView.findViewById(R.id.task_deadline)
        private val task_description: TextView = itemView.findViewById(R.id.task_description)
        private val button_edit: TextView = itemView.findViewById(R.id.button_edit)
        private val button_delete: TextView = itemView.findViewById(R.id.button_delete)

        fun bind(record: Task, gradeViewModel: GradeViewModel, applicationContext: Context) {
            task_name.text = record.task
            task_time.text = record.deadline
            task_description.text = record.description
            button_delete.setOnClickListener{
                gradeViewModel.delete(record)
            }
            button_edit.setOnClickListener {
                val intent = Intent(applicationContext, EditTask::class.java)
                (applicationContext as Activity).startActivityForResult(intent, EditCode)
                id = record.id!!

            }
        }

        companion object {
            fun create(parent: ViewGroup): TaskViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycleview_task, parent, false)
                return TaskViewHolder(view)
            }
        }
    }

    class TasksComparator : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.task == newItem.task
        }
    }

}