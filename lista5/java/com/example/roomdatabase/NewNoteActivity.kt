package com.example.roomdatabase

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewNoteActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_note)
        var note_name = findViewById<EditText>(R.id.note_name)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(note_name.text)) {

            }
            else {
                name = note_name.text.toString()
                replyIntent.putExtra("", name)
                setResult(Activity.RESULT_OK, replyIntent)

            }
            finish()
        }
    }
}