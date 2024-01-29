package com.example.roomdatabase

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText


class EditNote : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_note)
        val editNote = findViewById<EditText>(R.id.edit_note)

        val button = findViewById<Button>(R.id.button_edit_activity)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editNote.text)) {

            }
            else {
                name = editNote.text.toString()
                replyIntent.putExtra("", name)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}