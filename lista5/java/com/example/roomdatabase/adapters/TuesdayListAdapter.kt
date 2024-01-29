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
import com.example.roomdatabase.EditWeek
import com.example.roomdatabase.GradeViewModel
import com.example.roomdatabase.R
import com.example.roomdatabase.Tuesday
import com.example.roomdatabase.id

class TuesdayListAdapter(val gradeViewModel: GradeViewModel, val applicationContext: Context) : ListAdapter<Tuesday, TuesdayListAdapter.GradeViewHolder>(
    GradesComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeViewHolder {
        return GradeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: GradeViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, gradeViewModel, applicationContext)
    }

    class GradeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val weekName: TextView = itemView.findViewById(R.id.week_name)
        private val weekTime: TextView = itemView.findViewById(R.id.week_time)
        private val weekDescripton: TextView = itemView.findViewById(R.id.week_descrption)
        private val button_edit: TextView = itemView.findViewById(R.id.button_edit)
        private val button_delete: TextView = itemView.findViewById(R.id.button_delete)

        fun bind(record: Tuesday, gradeViewModel: GradeViewModel, applicationContext: Context) {
            weekName.text = record.grade
            weekTime.text = record.time
            weekDescripton.text = record.description
            button_delete.setOnClickListener{
                gradeViewModel.delete(record)
            }
            button_edit.setOnClickListener {
                val intent = Intent(applicationContext, EditWeek::class.java)
                (applicationContext as Activity).startActivityForResult(intent, EditCode)
                id = record.id!!

            }
        }

        companion object {
            fun create(parent: ViewGroup): GradeViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycleview_item, parent, false)
                return GradeViewHolder(view)
            }
        }
    }

    class GradesComparator : DiffUtil.ItemCallback<Tuesday>() {
        override fun areItemsTheSame(oldItem: Tuesday, newItem: Tuesday): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: Tuesday, newItem: Tuesday): Boolean {
            return oldItem.grade == newItem.grade
        }
    }

}