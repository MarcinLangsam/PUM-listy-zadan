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
import com.example.roomdatabase.EditNote
import com.example.roomdatabase.GradeViewModel
import com.example.roomdatabase.Note
import com.example.roomdatabase.R
import com.example.roomdatabase.id

class NoteListAdapter(val gradeViewModel: GradeViewModel, val applicationContext: Context) : ListAdapter<Note, NoteListAdapter.NoteViewHolder>(
    NotesComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, gradeViewModel, applicationContext)
    }


    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val gradeItemView: TextView = itemView.findViewById(R.id.note_name)
        private val button_edit: TextView = itemView.findViewById(R.id.button_edit)
        private val button_delete: TextView = itemView.findViewById(R.id.button_delete)

        fun bind(record: Note, gradeViewModel: GradeViewModel, applicationContext: Context) {
            gradeItemView.text = record.note
            button_delete.setOnClickListener{
                gradeViewModel.delete(record)
            }
            button_edit.setOnClickListener {
                val intent = Intent(applicationContext, EditNote::class.java)
                (applicationContext as Activity).startActivityForResult(intent, EditCode)
                id = record.id!!

            }
        }

        companion object {
            fun create(parent: ViewGroup): NoteViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycleview_note, parent, false)
                return NoteViewHolder(view)
            }
        }
    }

    class NotesComparator : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.note == newItem.note
        }
    }

}